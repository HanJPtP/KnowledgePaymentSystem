package com.woniu.outlet.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.pojo.CouponUserDetails;

/**
 * 优惠券-用户使用数据表: coupon_user_details
 * @author qk
 * @since 2022-06-16-16:29
 */
public interface CouponUserDetailsMapper extends BaseMapper<CouponUserDetails> {

    //根据用户id和优惠券id所在的那条数据id 修改该用户所持有的那张优惠券的状态
   default int updateCouponUserDetails(CouponUserDetails couponUserDetails){
       return this.updateById(couponUserDetails);
   }

}
