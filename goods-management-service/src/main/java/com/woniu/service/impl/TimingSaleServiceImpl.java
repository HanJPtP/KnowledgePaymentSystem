package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.po.TimingSale;
import com.woniu.outlet.mysql.mapper.TimingSaleMapper;
import com.woniu.service.ITimingSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimingSaleServiceImpl implements ITimingSaleService {

    private final TimingSaleMapper timingSaleMapper;

    /**
     * 根据 goods_sku ID 获得其对应的 timing_sale 信息
     * @param id
     * @return
     */
    @Override
    public TimingSale getTimingSaleByGoodsSkuId(Long id) {
        TimingSale timingSale = timingSaleMapper.getTimingSaleByGoodsSkuId(id);
        return timingSale;
    }

    /**
     * 根据 ID 删除 timing_sale
     * @param id
     * @return
     */
    @Override
    public int removeTimingSaleById(Long id) {
        return timingSaleMapper.deleteById(id);
    }

    /**
     * 查询在两小时内需要上架的任务列表
     * @return
     */
    @Override
    public List<TimingSale> listTimingSaleTask(Date startDate, Date endDate) {
        return timingSaleMapper.listTimingSaleTask(startDate, endDate);
    }
}
