/**
 * FileName: OrderController
 * Date:     2022/6/16 10:00
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.inlet.web.controller;

import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.order.dto.fo.OrderFO;
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
 * @date 2022/6/16 10:00
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final StringRedisTemplate redisTemplate;
    private final OrderService orderService;

    /**
     * 请求下单token
     * @param memberId
     * @return
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @GetMapping("/order_confirm")
    public ResponseResult<String> getIdempotentToken(@RequestParam("memberId") Long memberId){
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("order_page:"+uuid,"1",60*30, TimeUnit.SECONDS);
        return new ResponseResult<String>(200, "生成订单幂等token", uuid);
    }

    /**
     * 下单
     * @param orderFO
     * @return
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @PostMapping("/order")
    public ResponseResult<Long> addOrder (@RequestBody OrderFO orderFO){
        if (Boolean.FALSE.equals(redisTemplate.delete("order_page:" + orderFO.getToken()))){
            return new ResponseResult<>(4000,"重复请求or非法请求");
        }
        Long orderId = orderService.addOrder(orderFO);
        if (orderId > 0L ){
            return new ResponseResult<>(200, "下单成功", orderId);
        } else {
            return new ResponseResult<>(6000, "下单失败");
        }
    }

    /**
     * 支付
     * @param orderId
     * @return
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @PutMapping("/order/bill")
    public ResponseResult<Void> payTheOrder(@RequestParam("orderId") Long orderId){
        if (orderService.payTheBill(orderId)) {
            return new ResponseResult<>(200, "支付成功");
        } else {
            return new ResponseResult<>(5000, "支付失败or重复支付");
        }
    }


//     @PutMapping("/order/address")
//     public ResponseResult<Void> editAddress(@RequestBody AddressFO addressFO){
//         return null;
//     }
}