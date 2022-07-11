package com.woniu.inlet.web.controller;

import com.woniu.outlet.dao.mysql.pojo.CouponCollectionUsageData;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;
import com.woniu.service.impl.CouponCollectionUsageDataServiceImpl;
import com.woniu.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qk
 * @since 2022-06-18-14:47
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CouponCollectionUsageDataController {

    private final CouponCollectionUsageDataServiceImpl couponCollectionUsageDataService;


    /**
     * 分页查询所有时间段优惠券的使用情况
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/CouponCollectionUsageData/list")
    public ResponseResult<Object> getCouponCollectionUsageDataList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize) {
        PageInfo<CouponCollectionUsageData> pageInfo = couponCollectionUsageDataService.getCouponCollectionUsageDataList(pageNo, pageSize);
        ResponseResult<Object> result = null;
        if (pageInfo != null) {
            result = new ResponseResult<Object>(200, "ok", pageInfo);
        } else {
            result = new ResponseResult<Object>(500, "没有查到数据");
        }
        return result;

    }

    /**
     * 统计优惠券整体使用情况
     * @return
     */
    @GetMapping("/CouponCollectionUsageData/statistics")
    public ResponseResult<Object> couponStatistics(){
        CouponCollectionUsageData collectionUsageData = couponCollectionUsageDataService.couponStatistics();
        ResponseResult<Object> result = null;
        log.debug("collectionUsageData = {}",collectionUsageData);
        if (collectionUsageData != null){
            result = new ResponseResult<Object>(200, "ok", collectionUsageData);
        } else {
            result = new ResponseResult<Object>(500, "没有查到数据");
        }
        return result;
    }
}
