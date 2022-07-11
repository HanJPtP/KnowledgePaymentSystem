package com.woniu.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@EnableRabbit
@Configuration
public class RabbitMQConfig {
    //需要两个交换机
    //第一个交换机
    private static final String FIRST_EXCHANGE_NAME = "class.first.exchange";
    //第二个交换机
    private static final String SECOND_EXCHANGE_NAME = "class.second.exchange";
    //两个队列
    //第一个队列
    private static final String FIRST_QUEUE_NAME = "class.first.queue";
    //第二个队列
    private static final String SECOND_QUEUE_NAME = "class.second.queue";
    //两个routing key
    //第一个routing key
    private static final String FIRST_DEAD_ROUTING_KEY = "class.first.routing.key";
    //第二个routing key
    private static final String SECOND_REAL_ROUTING_KEY = "class.second.routing.key";

    //两个binding key
    //第一个binding key
    private static final String FIRST_BINDING_KEY = "class.first.binding.key";
    //第二个binding key
    private static final String SECOND_BINDING_KEY = "class.second.binding.key";

    //取出状态的队列
    private static final String USER_STATUS_NAME="user-status";
    //创建第一个交换机
    @Bean(FIRST_EXCHANGE_NAME)
    public Exchange firstExchange(){
        return new DirectExchange(FIRST_EXCHANGE_NAME);
    }
    //创建第二个交换机
    @Bean(SECOND_EXCHANGE_NAME)
    public Exchange secondExchange(){
        return new DirectExchange(SECOND_EXCHANGE_NAME);
    }
    //创建第一个队列,即死亡队列
    @Bean(FIRST_QUEUE_NAME)
    public Queue deathQueue(){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("x-dead-letter-exchange", SECOND_EXCHANGE_NAME);   // 指定时期后消息投递给哪个交换器。
        stringObjectHashMap.put("x-dead-letter-routing-key", SECOND_REAL_ROUTING_KEY);  // 指定到期后投递消息时以哪个路由键进行投递。
        stringObjectHashMap.put("x-message-ttl", 1800000);                            // 指定到期时间。1800秒
        return new Queue(FIRST_QUEUE_NAME, true,false,false, stringObjectHashMap);
    }
    //创建第二队列
    @Bean(SECOND_QUEUE_NAME)
    public Queue reallyQueue(){
        return new Queue(SECOND_QUEUE_NAME);
    }
    //创建他们之间的绑定关系
    @Bean(FIRST_BINDING_KEY)
    public Binding firstBinding(@Qualifier(FIRST_EXCHANGE_NAME) Exchange exchange,
                                @Qualifier(FIRST_QUEUE_NAME) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(FIRST_DEAD_ROUTING_KEY).noargs();
    }

    @Bean(SECOND_BINDING_KEY)
    public Binding secondBinding(@Qualifier(SECOND_EXCHANGE_NAME) Exchange exchange,
                                 @Qualifier(SECOND_QUEUE_NAME) Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(SECOND_REAL_ROUTING_KEY).noargs();
    }


    @Bean(USER_STATUS_NAME)
    public Queue userQueue(){
        return new Queue(USER_STATUS_NAME);
    }

}
