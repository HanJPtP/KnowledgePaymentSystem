package com.woniu.inlet.timer.receiver;

import com.woniu.inlet.timer.schedule.ScheduleTask;
import com.woniu.inlet.timer.thread.ThreadTaskAutoshelves;
import com.woniu.outlet.dao.mysql.po.TimingSale;
import com.woniu.service.impl.GoodsServiceImpl;
import com.woniu.service.impl.TimingSaleServiceImpl;
import com.woniu.util.EditTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class GoodsRabbitMqReciever {

    @Autowired
    private ScheduleTask scheduleTask;

    @Autowired
    private GoodsServiceImpl goodsService;

    @Autowired
    private ScheduledThreadPoolExecutor executor;

    @Autowired
    private TimingSaleServiceImpl timingSaleService;

    @RabbitListener(queues = "goodsAutoShelves")
    public void processQueueAutoShelves(String message) {
        Date startDate = new Date();
        Date endDate = EditTimeUtil.addDateMinutDate(startDate, 2);
        // 查询是否有在两个小时内需要上架的商品
        log.info("开始进入查询定时商品任务");
        queryTimingSale(startDate, endDate);
        log.info("结束进入查询定时商品任务");
        // 给延迟队列发送消息, 如果是在 00:00 - 02:00 则不发送
        int hours = startDate.getHours();
        if (hours > 0 && hours <= 2)
            return;
        log.info("开始发送可靠消息服务消息");
        scheduleTask.sendMessageToRabbitmq();
        log.info("发送可靠消息服务结束");
    }

    public void queryTimingSale(Date startDate, Date endDate) {
        List<TimingSale> timingSaleList = timingSaleService.listTimingSaleTask(startDate, endDate);
        if (timingSaleList.size() <= 0)
            return;
        log.info("进入进入下面判断, 判断时间开始");
        for (TimingSale timingSale : timingSaleList) {
            // 情况一: starttime 和 endtime 都不为空,则查询 starttime 是否在未来两小时内
            // 这种情况需要调用 goodsservice 更改goods_sku 状态 'y' 以及更新时间
            if ((timingSale.getStartTime().getTime() >= startDate.getTime()) && (timingSale.getStartTime().getTime() <= endDate.getTime()) && (timingSale.getEndTime() != null)) {
                ThreadTaskAutoshelves y = new ThreadTaskAutoshelves("y", timingSale.getId(), timingSale.getStartTime().getTime() - startDate.getTime(), goodsService, null);
                executor.schedule(y, y.getDelayMilliSeconds(), TimeUnit.MILLISECONDS);
            }
            // 情况二: starttime 和 endtime 都不为空,则查询 endtime 是否在未来两小时内
            // 调用 goodsservice 更改 goods_sku 状态 'n' 以及更新时间, 调用 timingsaleservice 删除当前数据
            if ((timingSale.getEndTime().getTime() >= startDate.getTime()) && (timingSale.getEndTime().getTime() <= endDate.getTime())) {
                ThreadTaskAutoshelves y = new ThreadTaskAutoshelves("n", timingSale.getId(), timingSale.getEndTime().getTime() - startDate.getTime(), goodsService, timingSaleService);
                executor.schedule(y, y.getDelayMilliSeconds(), TimeUnit.MILLISECONDS);
            }
            // 情况三: starttime 不为空, endtime 为空,则查询 starttime 是否在未来两小时内
            // 调用 goodsservice 更改 goods_sku 状态 'y' 以及更新时间, 调用 timingsaleservice 删除当前数据
            if ((timingSale.getStartTime().getTime() >= startDate.getTime()) && (timingSale.getStartTime().getTime() <= endDate.getTime()) && timingSale.getEndTime() == null) {
                ThreadTaskAutoshelves y = new ThreadTaskAutoshelves("y", timingSale.getId(), timingSale.getStartTime().getTime() - startDate.getTime(), goodsService, timingSaleService);
                executor.schedule(y, y.getDelayMilliSeconds(), TimeUnit.MILLISECONDS);
            }
        }
        log.info("进入进入下面判断, 判断时间结束");
        return;
    }

}
