package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;
import com.woniu.service.ICouponCollectionUsageDataService;
import com.woniu.outlet.dao.mysql.CouponCollectionUsageDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author QK
* @description 针对表【coupon_collection_usage_data】的数据库操作Service实现
* @createDate 2022-06-17 10:19:24
*/
@Service
@Transactional
@RequiredArgsConstructor
public class CouponCollectionUsageDataServiceImpl implements ICouponCollectionUsageDataService {

    private final CouponCollectionUsageDataMapper couponCollectionUsageDataMapper;

    //新增优惠券领取使用数据
    @Override
    public int addCouponCollectionUsageData(CouponCollectionUsageData couponCollectionUsageData) {
        return couponCollectionUsageDataMapper.insert(couponCollectionUsageData);
    }


    /**
     * 分页查询所有时间段优惠券的使用情况
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<CouponCollectionUsageData> getCouponCollectionUsageDataList(Integer pageNo, Integer pageSize) {

        //开启分页
        Page<CouponCollectionUsageData> page = new Page<>(pageNo,pageSize);
        //将获取到的page对象 传递到自己写的sql查询方法  注意: 返回值类型一定要是IPage<T>
        Page<CouponCollectionUsageData> list = couponCollectionUsageDataMapper.getCouponCollectionUsageDataList(page);
        //封装到PageInfo对象
        PageInfo<CouponCollectionUsageData> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 统计优惠券整体使用情况
     * @return
     */
    @Override
    public CouponCollectionUsageData couponStatistics() {
        return couponCollectionUsageDataMapper.couponStatistics();
    }
}
