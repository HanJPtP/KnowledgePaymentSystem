package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.outlet.dao.mysql.pojo.QkMarketingDetails;

/**
 * <p>
 *  营销活动详情表 服务类
 * </p>
 *
 * @author qk
 * @since 2022-06-13
 */
public interface IQkMarketingDetailsService {

    //新增营销活动
    int addMarketingDetails(QkMarketingDetails marketingDetails);

}
