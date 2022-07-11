package com.woniu.service;

import com.woniu.outlet.dao.mysql.pojo.CouponDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;

import java.util.List;

/**
 * <p>
 *  优惠券详情表服务类
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
public interface ICouponDetailsService {

    //新增优惠券
    int addcoupon(CouponDetails couponDetails);

    //修改优惠券信息
    int updateCoupon(CouponDetails couponDetails);

    //多条件查询带分页 查询优惠券
    PageInfo<CouponDetails> getCouponDetailsList(Integer pageNo,
                                                 Integer pageSize,
                                                 Integer couponType,
                                                 Integer couponStatus,
                                                 Integer channelsId,
                                                 String couponName);
}