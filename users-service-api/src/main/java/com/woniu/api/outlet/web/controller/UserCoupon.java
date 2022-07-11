package com.woniu.api.outlet.web.controller;

import com.woniu.api.util.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *  用户优惠券接口
 */
public interface UserCoupon {
    /**
     *  查询所有优惠券信息
     * @return
     */
    @PostMapping("usermember/listcoupon")
    public ResponseResult<Object> selectAllCoupon();
}
