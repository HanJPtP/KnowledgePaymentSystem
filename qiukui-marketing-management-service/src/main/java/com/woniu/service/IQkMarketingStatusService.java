package com.woniu.service;

import com.woniu.outlet.dao.mysql.pojo.QkMarketingStatus;

import java.util.List;

/**
 * <p>
 *  营销活动状态表
 * </p>
 *
 * @author qk
 * @since 2022-06-13
 */
public interface IQkMarketingStatusService {

    //查询所有的营销活动的详情状态  在前端下拉列表使用的
    List<QkMarketingStatus> getMarketingStatus();


}
