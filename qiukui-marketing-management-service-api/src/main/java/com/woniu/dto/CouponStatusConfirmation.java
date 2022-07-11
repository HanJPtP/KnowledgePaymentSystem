package com.woniu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 优惠券状态确认
 * @author qk
 * @since 2022-06-18-14:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponStatusConfirmation {
    /**
     * 优惠券-用户使用数据表: coupon_user_details 的id
     */
    private Long couponUserDetailsId;
    /**
     * 订单号
     */
    private Long orderNumber;
    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

}
