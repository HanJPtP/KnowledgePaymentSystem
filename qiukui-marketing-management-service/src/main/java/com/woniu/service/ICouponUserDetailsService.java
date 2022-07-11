package com.woniu.service;

import com.woniu.dto.CouponQueryResult;
import com.woniu.outlet.dao.mysql.pojo.CouponUserDetails;

/**
 * 优惠券-用户使用数据表: coupon_user_details
 * @author qk
 * @since 2022-06-18-14:16
 */
public interface ICouponUserDetailsService {

    //验证订单传过来的优惠券id是否存在,若存在就计算好优惠后的订单金额和优惠券的金额,然后返回
//    CouponQueryResult calculateOrderCoupons(Long id,)

    //根据用户id和优惠券id所在的那条数据id 修改该用户所持有的那张优惠券的状态
    int updateCouponUserDetails(CouponUserDetails couponUserDetails);

    //根据用户id和优惠券id所在的那条数据id 查询该用户所持有的那张优惠券
    CouponUserDetails getCouponUserDetailsById(Long couponUserDetailsId);

}
