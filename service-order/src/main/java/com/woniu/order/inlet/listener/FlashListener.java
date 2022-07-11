/**
 * FileName: FlashListener
 * Date:     2022/6/22 19:25
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.inlet.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.message.entity.dto.MessageVO;
import com.woniu.order.dto.fo.FlashFO;
import com.woniu.order.outlet.client.MessageClient;
import com.woniu.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/22 19:25
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class FlashListener {

    private ObjectMapper objectMapper= new ObjectMapper();
    private final OrderService orderService;
    private final MessageClient messageClient;

    @RabbitListener(queues = "flash-order")
    public void flashQueue(String msgStr){

        MessageVO messageVO = null;
        try {
            messageVO = objectMapper.readValue(msgStr, MessageVO.class);
            String content = messageVO.getContent();
            FlashFO flashFO = objectMapper.readValue(content, FlashFO.class);
            orderService.addFlashOrder(flashFO);

            messageClient.receiveMessage(messageVO.getId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}