package com.woniu.outlet.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * 优惠券领取使用数据表
* @author QK
* @description 针对表【coupon_collection_usage_data】的数据库操作Mapper
* @createDate 2022-06-17 10:19:24
* @Entity com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData
*/
public interface CouponCollectionUsageDataMapper extends BaseMapper<CouponCollectionUsageData> {

    /**
     * 根据当前日期 和对应优惠券id查询该优惠券该天的领取详细信息
     * @param couponDetailsId
     * @return
     */
    @Select("select * from coupon_collection_usage_data where couponDetailsId = #{couponDetailsId} and currentTime = #{currenttime}")
    CouponCollectionUsageData getCouponCollectionUsageDataByIdAndTime(@Param("couponDetailsId") Long couponDetailsId,
                                                                      @Param("currenttime") String currenttime);


    /**
     * 查询所有时间段优惠券的使用情况 带分页
     * @param page
     * @return
     */
    default Page<CouponCollectionUsageData> getCouponCollectionUsageDataList(Page<CouponCollectionUsageData> page){
        return this.selectPage(page,null);
    }

    /**
     * 统计优惠券整体使用情况
     * @return
     */
    @Select("select sum(numberOfSheetsReceived) numberOfSheetsReceived,sum(numberOfRecipients) numberOfRecipients,sum(numberOfSheetsUsed) numberOfSheetsUsed, sum(numberOfUsers) numberOfUsers  from coupon_collection_usage_data")
    CouponCollectionUsageData couponStatistics();




}
