package com.woniu.intnet.web.fo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMQSendMsgFo implements Runnable{

    private RabbitTemplate rabbitTemplate;

    private String exchangeName;

    private String routingKeyName;

    private String msg;


    public RabbitMQSendMsgFo(String exchangeName, String routingKeyName, String msg, RabbitTemplate rabbitTemplate){
        this.exchangeName=exchangeName;
        this.rabbitTemplate=rabbitTemplate;
        this.msg=msg;
        this.routingKeyName=routingKeyName;
    }

    @Override
    public void run() {
        System.out.println("开始执行发消息给延迟队列");
        rabbitTemplate.convertAndSend(exchangeName,routingKeyName,msg);
        System.out.println("发消息结束");
    }

}
