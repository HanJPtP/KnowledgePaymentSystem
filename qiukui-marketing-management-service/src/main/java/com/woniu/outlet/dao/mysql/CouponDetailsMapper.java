package com.woniu.outlet.dao.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.inlet.web.dto.CouponDetailsDTO;
import com.woniu.outlet.dao.mysql.pojo.CouponDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
public interface CouponDetailsMapper extends BaseMapper<CouponDetails> {


    //多条件查询带分页 查询优惠券
    default Page<CouponDetails> getCouponDetailsList(Page<CouponDetails> page,Integer couponTypeId, Integer couponStatus, Integer channelsId, String couponName){
        QueryWrapper<CouponDetails> queryWrapper = new QueryWrapper<CouponDetails>();
        if (couponTypeId >= 0){
            queryWrapper.eq("couponTypeId",couponTypeId);
        }
        if (couponStatus >= 0){
            queryWrapper.eq("couponStatus",couponStatus);
        }
        if (channelsId >= 0){
            queryWrapper.eq("channelsId",channelsId);
        }
        if (couponName != null && couponName.length()>0) {
            queryWrapper.like("couponName",couponName);
        }
        return this.selectPage(page,queryWrapper);
    }






}
