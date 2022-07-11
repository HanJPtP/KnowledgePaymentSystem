package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;
import org.apache.ibatis.annotations.Select;


/**
 * 优惠券领取使用数据表
* @author QK
* @description 针对表【coupon_collection_usage_data】的数据库操作Service
* @createDate 2022-06-17 10:19:24
*/
public interface ICouponCollectionUsageDataService {

    //新增优惠券领取使用数据
    int addCouponCollectionUsageData(CouponCollectionUsageData couponCollectionUsageData);


    /**
     * 分页查询所有时间段优惠券的使用情况
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<CouponCollectionUsageData> getCouponCollectionUsageDataList(Integer pageNo,Integer pageSize);

    /**
     * 统计优惠券整体使用情况
     * @return
     */
    CouponCollectionUsageData couponStatistics();

}
