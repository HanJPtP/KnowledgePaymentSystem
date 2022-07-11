package com.woniu.users.inlet.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.api.outlet.web.entity.PointsInfoToUserCenter;
import com.woniu.message.entity.dto.MessageVO;
import com.woniu.users.outlet.dao.mysql.mapper.MessageidMapper;
import com.woniu.users.outlet.dao.mysql.po.Messageid;
import com.woniu.users.service.impl.PointRuleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;

/**
 *  接收消息
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ReceiveOrderInfo {

    private final PointRuleServiceImpl pointRuleService;

    private final MessageidMapper messageidMapper;



    /**
     *  接收订单消息
     * @param msg
     */
    @RabbitListener(queues = "user-order")
    public void receiveOrderInfo(String msg){
        System.out.println("msg接收" + msg);
        ObjectMapper objectMapper = new ObjectMapper();
        StringReader stringReader = new StringReader(msg);
        try {
            MessageVO messageVO = objectMapper.readValue(stringReader, MessageVO.class);
            // 确认接收消息，新增消息去重表信息，新增成功执行代码，新增失败说明收到重复的消息，不执行代码
            int rs = messageidMapper.insert(new Messageid(messageVO.getId()));
            if(rs <= 0){
                // 新增失败，说是重复消息
                return;
            }
            StringReader content = new StringReader(messageVO.getContent());
            PointsInfoToUserCenter pointsInfoToUserCenter = objectMapper.readValue(content, PointsInfoToUserCenter.class);
            pointRuleService.getOrderMsg(pointsInfoToUserCenter);

            System.out.println("订单对象：" + pointsInfoToUserCenter.toString());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
