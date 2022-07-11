package com.woniu.inlet.web.controller;


import com.woniu.dto.CouponInfoToEventCenter;
import com.woniu.dto.CouponQueryResult;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.outlet.dao.mysql.pojo.CouponDetails;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;
import com.woniu.service.impl.CouponDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券相关
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
@RestController
@RequiredArgsConstructor
public class CouponDetailsController {
    private final CouponDetailsServiceImpl couponDetailsService;

    //新增优惠券
//    @PreAuthorize("hasAuthority('cashier:live:list')")
    @PostMapping("CouponDetails/add")
    public ResponseResult<Void> addcoupon(@RequestBody CouponDetails couponDetails) {
        ResponseResult<Void> result = new ResponseResult<>();
        int i = couponDetailsService.addcoupon(couponDetails);
        if (i > 0) {
            result = new ResponseResult<Void>(200, "添加成功");
        } else {
            result = new ResponseResult<Void>(500, "添加失败");
        }
        return result;
    }

    //修改优惠券信息
//    @PreAuthorize("hasAuthority('cashier:live:list')")
    @PostMapping("CouponDetails/update")
    public ResponseResult<Void> updateCoupon(@RequestBody CouponDetails couponDetails) {
        int i = couponDetailsService.updateCoupon(couponDetails);
        ResponseResult<Void> result = null;
        if (i > 0){
            result = new ResponseResult<>(200,"修改成功!");
        }else {
            result = new ResponseResult<>(500,"修改失败!");
        }
        return result;

    }

    //多条件查询带分页 查询优惠券
//    @PreAuthorize("hasAuthority('cashier:live:list')")
    @PostMapping("CouponDetails/query")
    public ResponseResult<Object> getCouponDetailsList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
                                                       @RequestParam(value = "couponType", required = false, defaultValue = "-1") Integer couponType,
                                                       @RequestParam(value = "couponStatus", required = false, defaultValue = "-1") Integer couponStatus,
                                                       @RequestParam(value = "channelsId", required = false, defaultValue = "-1") Integer channelsId,
                                                       @RequestParam(value = "couponName", required = false, defaultValue = "") String couponName) {
        PageInfo<CouponDetails> pageInfo = couponDetailsService.getCouponDetailsList(pageNo, pageSize, couponType, couponStatus, channelsId, couponName);
        ResponseResult<Object> result = null;
        if (pageInfo != null) {
            result = new ResponseResult<Object>(200, "ok", pageInfo);
        } else {
            result = new ResponseResult<Object>(500, "没有查到数据");
        }
        return result;

    }

    /**
     * 查询优惠券折扣
     * @param couponInfoToEventCenter 优惠券使用条件
     * @return 返回优惠券使用结果
     */
    public ResponseResult<CouponQueryResult> fixCouponDiscount(CouponInfoToEventCenter couponInfoToEventCenter){



        return null;
    }

}

