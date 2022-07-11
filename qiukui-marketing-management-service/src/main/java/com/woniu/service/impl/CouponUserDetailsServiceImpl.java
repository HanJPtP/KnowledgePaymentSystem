package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.CouponUserDetailsMapper;
import com.woniu.outlet.dao.mysql.pojo.CouponUserDetails;
import com.woniu.service.ICouponUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 优惠券-用户使用数据表: coupon_user_details
 * @author qk
 * @since 2022-06-21-19:25
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CouponUserDetailsServiceImpl implements ICouponUserDetailsService {
    private final CouponUserDetailsMapper couponUserDetailsMapper;


    /**
     * 根据用户id和优惠券id所在的那条数据id 修改该用户所持有的那张优惠券的状态
     * @param couponUserDetails
     * @return
     */
    @Override
    public int updateCouponUserDetails(CouponUserDetails couponUserDetails) {
        return couponUserDetailsMapper.updateCouponUserDetails(couponUserDetails);
    }


    /**
     * 根据用户id和优惠券id所在的那条数据id 查询该用户所持有的那张优惠券
     * @param couponUserDetailsId
     * @return
     */
    @Override
    public CouponUserDetails getCouponUserDetailsById(Long couponUserDetailsId) {
        return couponUserDetailsMapper.selectById(couponUserDetailsId);
    }
}
