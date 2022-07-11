package com.woniu.service;

import com.woniu.outlet.dao.mysql.po.TimingSale;

import java.util.Date;
import java.util.List;

public interface ITimingSaleService {

    // 根据 goods_sku ID 获得其对应的 timing_sale 信息
    TimingSale getTimingSaleByGoodsSkuId(Long id);

    // 根据 ID 删除 timing_sale
    int removeTimingSaleById(Long id);

    // 查询在两小时内需要上架的任务列表
    List<TimingSale> listTimingSaleTask(Date startDate, Date endDate);

}
