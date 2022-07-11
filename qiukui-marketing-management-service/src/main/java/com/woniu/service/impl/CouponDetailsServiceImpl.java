package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outlet.dao.mysql.pojo.CouponDetails;
import com.woniu.outlet.dao.mysql.CouponDetailsMapper;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;
import com.woniu.service.ICouponDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CouponDetailsServiceImpl implements ICouponDetailsService {

    private final CouponDetailsMapper couponDetailsMapper;

    //新增优惠券
    @Override
    public int addcoupon(CouponDetails couponDetails) {
        return couponDetailsMapper.insert(couponDetails);
    }

    //修改优惠券
    @Override
    public int updateCoupon(CouponDetails couponDetails) {
        return couponDetailsMapper.updateById(couponDetails);
    }

    /**
     * //多条件查询带分页 查询优惠券
     * @param pageNo
     * @param pageSize
     * @param couponTypeId
     * @param couponStatus
     * @param channelsId
     * @param couponName
     * @return
     */
    @Override
    public PageInfo<CouponDetails> getCouponDetailsList(Integer pageNo,
                                                        Integer pageSize,
                                                        Integer couponTypeId,
                                                        Integer couponStatus,
                                                        Integer channelsId,
                                                        String couponName) {
        //开启分页
        Page<CouponDetails> page = new Page<>(pageNo, pageSize);
        // 将获取到的page对象和前台传递的参数传递到自己写的sql查询方法，注意：返回值类型一定要是IPage<T>
        IPage<CouponDetails> couponDetailsList = couponDetailsMapper.getCouponDetailsList(page, couponTypeId, couponStatus, channelsId, couponName);
        //封装到PageInfo对象
        PageInfo<CouponDetails> pageInfo = new PageInfo<>(couponDetailsList);
        return pageInfo;
    }


}
