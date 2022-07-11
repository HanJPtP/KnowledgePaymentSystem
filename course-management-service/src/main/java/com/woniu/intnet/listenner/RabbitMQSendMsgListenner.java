package com.woniu.intnet.listenner;


import com.woniu.intnet.web.fo.ClassCurrencyMsgUpdateFo;
import com.woniu.outnet.dao.elasticserch.ClassCurrencyMsgRepository;
import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;
import com.woniu.service.impl.ClassCurrencyMsgServiceImpl;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RabbitMQSendMsgListenner {
    @Resource
    private ClassCurrencyMsgServiceImpl classCurrencyMsgService;
    @Resource
    private ClassCurrencyMsgRepository classCurrencyMsgRepository;

    @SneakyThrows
    @RabbitListener(queues = "class.second.queue")
    private void classSecondQueueListenner(String msg) {
        String[] split = msg.split(":");
        Long id = Long.parseLong(split[0]);
        ClassCurrencyMsg classCurrencyMsg = classCurrencyMsgService.listClassCurrencyMsgById(id);
        if (classCurrencyMsg.getStatus().equals(split[1])) {
            return;
        }
        classCurrencyMsg.setStatus(split[1]);
        //判断状态
        if (split[1].equals("1")) {
            //如果是定时上架任务
            //修改上架时间
            classCurrencyMsg.setGroundingtime(classCurrencyMsg.getStarttime());
            //修改定时上架时间为null
            classCurrencyMsg.setStarttime(null);
            //修改状态
            classCurrencyMsg.setStatus(split[1]);
        }else if(split[1].equals("2")){
            //如果是定时下架任务
            //修改下架时间
            classCurrencyMsg.setOffshelftime(classCurrencyMsg.getEndtime());
            //修改定时下架时间为null
            classCurrencyMsg.setEndtime(null);
            //修改状态
            classCurrencyMsg.setStatus(split[1]);
        }
        ClassCurrencyMsgUpdateFo classCurrencyMsgUpdateFo = new ClassCurrencyMsgUpdateFo();
        BeanUtils.copyProperties(classCurrencyMsg,classCurrencyMsgUpdateFo );
        int i = classCurrencyMsgService.updateClassCurrencyMsgAllCanNull(classCurrencyMsgUpdateFo);
        if(i>0){
            classCurrencyMsgRepository.save(classCurrencyMsg);
        }
    }
}
