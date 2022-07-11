package com.woniu.controller;

import com.woniu.dto.CouponInfoToEventCenter;
import com.woniu.dto.CouponQueryResult;
import com.woniu.dto.ResponseResult;

/**
 * @author qk
 * @since 2022-06-17-15:26
 */
public interface IDemoToEvent {
    /**
     * 查询优惠券折扣
     * @param couponInfoToEventCenter 优惠券使用条件
     * @return 返回优惠券使用结果
     */
    ResponseResult<CouponQueryResult> fixCouponDiscount(CouponInfoToEventCenter couponInfoToEventCenter);
}
