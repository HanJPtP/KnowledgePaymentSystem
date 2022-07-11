package com.woniu.inlet.timer;

import com.woniu.outlet.dao.mysql.CouponCollectionUsageDataMapper;
import com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qk
 * @since 2022-06-17-15:00
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class TimedTask {

    private final CouponCollectionUsageDataMapper couponCollectionUsageDataMapper;

    //每天的00:01:00 就往 优惠券领取使用数据表: coupon_collection_usage_data
    // 添加一条 类型为3 随机金额券的 统计数据,然后开始一天的统计

    @Scheduled(cron = "0 1 00 * * ?")  //"0 0 12 * * ?" 每天00:01点触发  创建一条数据
    public void addCouponCollectionUsageData(){

        System.out.println("定时器中创建的currenttime 当前时间字段为 : " + new Date());
        CouponCollectionUsageData couponCollectionUsageData = CouponCollectionUsageData.builder()
                .coupontypeid(3)
                .couponDetailsId(1L) //100块随机金额券
                .numberofsheetsreceived(0)  //领取张数
                .numberofrecipients(0) //领取人数
                .numberofsheetsused(0) //使用张数
                .numberofusers(0) //使用人数
                .currenttime(new Date()) //当前时间
                .build();

        //每天 每天00:01 创建一条数据 记录 100块随机金额券的使用情况 多少人领取, 多少人使用
        couponCollectionUsageDataMapper.insert(couponCollectionUsageData);
    }

}
