package com.woniu.api.outlet.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName user_coupon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponDto implements Serializable {
    /**
     * 用户id
     */
    private Long uid;

    /**
     * 优惠券id
     */
    private Long couponid;

    /**
     * 优惠券状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}