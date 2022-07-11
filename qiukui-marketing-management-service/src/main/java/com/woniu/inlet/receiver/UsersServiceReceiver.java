package com.woniu.inlet.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.message.entity.dto.MessageVO;
import com.woniu.outlet.client.MessageClient;
import com.woniu.outlet.dao.mysql.CouponCollectionUsageDataMapper;
import com.woniu.outlet.dao.mysql.CouponUserDetailsMapper;
import com.woniu.outlet.dao.mysql.UnrepeatedMessageMapper;
import com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData;
import com.woniu.outlet.dao.mysql.pojo.CouponUserDetails;
import com.woniu.outlet.dao.mysql.pojo.UnrepeatedMessage;
import com.woniu.util.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author qk
 * @since 2022-06-16-11:00
 */
@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class UsersServiceReceiver {

    private final UnrepeatedMessageMapper unrepeatedMessageMapper;

    private final CouponUserDetailsMapper couponUserDetailsMapper;

    private final CouponCollectionUsageDataMapper couponCollectionUsageDataMapper;

    private final MessageClient messageClient;

    @RabbitListener(queues = "Q1")
    public void handler(String messageStr){
        MessageVO messageVO = null;
        try {
            messageVO = new ObjectMapper().readValue(messageStr, MessageVO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("收到从Q1队列的消息为: " + messageVO);

        String[] split = messageVO.getContent().split(":");
        String messageIdStr = split[0];
        String userIdStr=split[1];
        String email = split[2];
        log.debug("messageIdStr = {}",messageIdStr);
        log.debug("userIdStr = {}",userIdStr);
        log.debug("email = {}",email );
        try {
            //消息去重
            unrepeatedMessageMapper.insert(new UnrepeatedMessage(Long.parseLong(messageIdStr)));

        } catch (RuntimeException e) {
            log.debug("消息 {} 是重复消息",messageIdStr);
            return;
        }

        //创建一个优惠券-用户使用数据对象
        CouponUserDetails couponUserDetails = CouponUserDetails.builder()
                .couponTypeId(3) //优惠券类型  新用户为随机金额券3
                .coupondetailsId(1L) //指定优惠券 100块随机金额券
                .usersId(Long.parseLong(userIdStr)) //用户id
                .pickUpTime(LocalDateTime.now()) //领取时间
                .couponUsageStatus(0) //优惠券使用状态(0:未使用,1: 已使用,2: 已过期)
                .userEmail(email) //用户邮箱
                .build();

        //将传过来的用户id,邮箱都存入 优惠券-用户使用数据表
        couponUserDetailsMapper.insert(couponUserDetails);


        //根据 100块随机金额券的id 查出 记录它当天的那条数据列  注: 这条查询仅针对 100块随机金额券!
        CouponCollectionUsageData couponCollectionUsageData = null;
        try {

            System.out.println("UsersServiceReceiver 中的 当前时间为: "  + new Date());
            String currenttime = DateFormatUtils.DateToString(new Date(), "yyyy-MM-dd");
//            Date currenttime = DateFormatUtils.StringToDate("yyyy-MM-dd", s);

            couponCollectionUsageData = couponCollectionUsageDataMapper.getCouponCollectionUsageDataByIdAndTime(1L,currenttime);
            //每给一个用户发100块随机金额券,就在 优惠券领取使用数据表: coupon_collection_usage_data 中 给领取张数,领取人数 分别 + 1
            couponCollectionUsageData.setNumberofsheetsreceived(couponCollectionUsageData.getNumberofsheetsreceived() + 1);
            couponCollectionUsageData.setNumberofrecipients(couponCollectionUsageData.getNumberofrecipients() + 1);
            couponCollectionUsageDataMapper.updateById(couponCollectionUsageData);
        } catch (Exception e) {
            log.debug("没有查到100块随机金额券的当天使用数据记录表!");
            e.printStackTrace();
            return;
        }


        //消息确认
        messageClient.receiveMessage(messageVO.getId());
//
//        try {
//            mailUtils.sendSimpleMail(email,"优惠券","欢迎注册,给你发了一张优惠券,请注意查收!");
//            log.debug("优惠券发送成功!");
//        } catch (Exception e) {
//            log.debug("给用户 {} 发送的提醒领优惠券邮件发送失败,请检查原因",email);
//        }


    }
}
