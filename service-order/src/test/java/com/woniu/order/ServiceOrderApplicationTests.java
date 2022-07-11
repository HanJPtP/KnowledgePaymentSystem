package com.woniu.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.dto.CouponInfoToEventCenter;
import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.order.outlet.client.MessageClient;
import com.woniu.order.outlet.dao.mysql.mapper.CartItemMapper;
import com.woniu.order.outlet.dao.mysql.po.CartItem;
import com.woniu.order.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
class ServiceOrderApplicationTests {

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private MessageClient messageClient;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    private final String FLASH_EVENT = "flash_event";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    void demo () throws JsonProcessingException {
        CouponInfoToEventCenter couponInfoToEventCenter = CouponInfoToEventCenter.builder()
                .couponId(1001L)
                .memberId(1001L)
                .totalAmount(new BigDecimal("200.5"))
                .build();

        String jsonStr = objectMapper.writeValueAsString(couponInfoToEventCenter);
        MessageDTO toEventCenter = MessageDTO.builder().exchange("order_center.direct")
                .routingKey("ToEventCenter")
                .contactMail("872160139@qq.com")
                .content(jsonStr)
                .build();
        messageClient.saveMessage(toEventCenter);


    }

    @Test
    void flashTest(){
        Long increment = redisTemplate.opsForHash().increment(FLASH_EVENT, String.valueOf(10086), 1001);
        log.info("返回结果为 {}", increment);
    }
}
