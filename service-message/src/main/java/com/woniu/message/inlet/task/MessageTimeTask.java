/**
 * FileName: MessageTimeTask
 * Date:     2022/6/10 10:46
 * Author: YuanXQ
 * Description:
 */
package com.woniu.message.inlet.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.message.entity.dto.MessageVO;
import com.woniu.message.outlet.dao.mysql.mapper.MessageMapper;
import com.woniu.message.outlet.dao.mysql.po.Message;
import com.woniu.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/10 10:46
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MessageTimeTask {

    private final MessageService messageService;
    private final RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * <p>
     * 定时任务发送消息
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/10 16:45
     */
    @Scheduled(fixedDelay = 1000 * 10)
    public void sendMessage(){
        // 查询所有待发送消息
        List<Message> messageList = messageService.listMessagesNotSend();
        // 没有待发送消息则直接结束该轮任务
        if (messageList.isEmpty()) {
            log.info("没有待发送消息");
            return;
        }
        // 发送消息
        messageList.forEach(message -> {

            MessageVO messageVO = MessageVO.builder().id(message.getId()).content(message.getContent()).build();
            try {
                String messageStr = objectMapper.writeValueAsString(messageVO);

                log.info("待发消息为: {}", messageStr);
                CorrelationData data = new CorrelationData(message.getId().toString());
                rabbitTemplate.convertAndSend(message.getExchange(), message.getRoutingKey(),messageStr, data);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        );

    }
}