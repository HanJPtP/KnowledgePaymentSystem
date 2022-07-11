/**
 * FileName: RabbitMQConfig
 * Date:     2022/6/10 9:42
 * Author: YuanXQ
 * Description:
 */
package com.woniu.message.config;

import com.woniu.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/10 9:42
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class RabbitMQConfig {

    private final MessageService messageService;

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        // Exchange确认
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息 {} 已发送至 Exchange", correlationData.getId());
                messageService.setMessageSuccess(new Long(correlationData.getId()));
            } else {
                log.info("消息 {} 未能发送到 Exchange, {}", correlationData.getId(), cause);
                messageService.setRetryCountMinus(new Long(correlationData.getId()));
                // TODO: 发送邮件给联系人
            }
        });
        return rabbitTemplate;
    }
}