package com.woniu.inlet.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.dto.CouponInfoToEventCenter;
import com.woniu.message.entity.dto.MessageVO;
import com.woniu.outlet.client.MessageClient;
import com.woniu.outlet.dao.mysql.CouponCollectionUsageDataMapper;
import com.woniu.outlet.dao.mysql.UnrepeatedMessageMapper;
import com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData;
import com.woniu.outlet.dao.mysql.pojo.CouponUserDetails;
import com.woniu.outlet.dao.mysql.pojo.UnrepeatedMessage;
import com.woniu.service.impl.CouponCollectionUsageDataServiceImpl;
import com.woniu.service.impl.CouponUserDetailsServiceImpl;
import com.woniu.util.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author qk
 * @since 2022-06-21-19:19
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class OrderServiceReceiver {
    private final CouponUserDetailsServiceImpl couponUserDetailsService;
    private final CouponCollectionUsageDataMapper couponCollectionUsageDataMapper;
    private final MessageClient messageClient;

    private final UnrepeatedMessageMapper unrepeatedMessageMapper;

    @RabbitListener(queues = "Q2")
    public void handler(String msg){

        MessageVO messageVO = null;
        try {
            messageVO = new ObjectMapper().readValue(msg, MessageVO.class);
            System.out.println("收到从Q2队列的消息为: " + messageVO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            //消息去重
            unrepeatedMessageMapper.insert(new UnrepeatedMessage(messageVO.getId()));
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.debug("消息 {} 是重复消息",messageVO.getId());
            return;
        }

        //接收袁雄起传过来的参数用的实体类
        CouponInfoToEventCenter couponInfoToEventCente = null;
        try {
            couponInfoToEventCente = new ObjectMapper().readValue(messageVO.getContent(),CouponInfoToEventCenter.class);
            System.out.println("messageVO.getContent() = " + couponInfoToEventCente);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        //根据id查询到该用户对应的那张优惠券
        CouponUserDetails couponUserDetails = couponUserDetailsService.getCouponUserDetailsById(couponInfoToEventCente.getCouponId());
        couponUserDetails.setCouponUsageStatus(1); //将优惠券的状态改为已使用
        couponUserDetailsService.updateCouponUserDetails(couponUserDetails);

        //优惠券领取使用数据表: coupon_collection_usage_data
        //将优惠券统计数据的 使用张数和使用人数 + 1
        //根据 100块随机金额券的id 查出 记录它当天的那条数据列  注: 这条查询仅针对 100块随机金额券!
        CouponCollectionUsageData couponCollectionUsageData = null;
        try {
            System.out.println("UsersServiceReceiver 中的 当前时间为: "  + new Date());
            String currenttime = DateFormatUtils.DateToString(new Date(), "yyyy-MM-dd");
            couponCollectionUsageData = couponCollectionUsageDataMapper.getCouponCollectionUsageDataByIdAndTime(1L,currenttime);
            //用户每使用一张,就在 优惠券领取使用数据表: coupon_collection_usage_data 中 给使用张数,使用人数 分别 + 1
            couponCollectionUsageData.setNumberofsheetsused(couponCollectionUsageData.getNumberofsheetsused() + 1);
            couponCollectionUsageData.setNumberofusers(couponCollectionUsageData.getNumberofusers() + 1);
            couponCollectionUsageDataMapper.updateById(couponCollectionUsageData);
        } catch (Exception e) {
            log.debug("没有查到100块随机金额券的当天使用数据记录表!");
            e.printStackTrace();
            return;
        }

        //消息确认
        messageClient.receiveMessage(messageVO.getId());


    }

}
