/**
 * FileName: FlashController
 * Date:     2022/6/22 15:09
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.inlet.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.order.dto.fo.FlashFO;
import com.woniu.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/22 15:09
 * @since 1.0.0
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class FlashController {

    private final String FLASH_EVENT = "flash_event";
    private final String PRODUCT_UUID = "product_sku:";
    private final StringRedisTemplate redisTemplate;
    private final OrderService orderService;

    /**
     * <p>
     * 秒杀下单
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/22 18:38
     * @param token
     * @param flashFO
     * @return com.woniu.knowledgepayment.commons.entity.ResponseResult<java.lang.Void>
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @PostMapping("/flash")
    public ResponseResult<Void> flashOrder(@RequestParam("token") String token,
                                           @RequestBody FlashFO flashFO) {
        if (Boolean.FALSE.equals(redisTemplate.delete(PRODUCT_UUID+token))) {
            return new ResponseResult<>(4000, "请求非法or重复请求");
        }

        if (redisTemplate.opsForHash().increment(FLASH_EVENT, String.valueOf(flashFO.getProductSkuId()), -1) < 0) {
            redisTemplate.opsForHash().delete(FLASH_EVENT, String.valueOf(flashFO.getProductSkuId()));
            return new ResponseResult<>(4000, "已售罄");
        };

        orderService.sendFlashMessage(flashFO);
        return new ResponseResult<>(200, "下单成功, 请稍后");
    }

    /**
     * <p>
     * 申请秒杀订单幂等token
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/22 18:37
     * @param
     * @return com.woniu.knowledgepayment.commons.entity.ResponseResult<java.lang.String>
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @GetMapping("/product/detail")
    public ResponseResult<String> requireToken() {
        UUID uuid = UUID.randomUUID();
        redisTemplate.opsForValue().set(PRODUCT_UUID+uuid, String.valueOf(1),60*15, TimeUnit.SECONDS);
        return new ResponseResult<String>(200, "幂等token", uuid.toString());
    }
}