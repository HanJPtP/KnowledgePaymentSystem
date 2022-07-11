package com.woniu.service.impl;


import com.woniu.outlet.dao.mysql.QkMarketingStatusMapper;
import com.woniu.outlet.dao.mysql.pojo.QkMarketingStatus;
import com.woniu.service.IQkMarketingStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qk
 * @since 2022-06-13
 */
@Service
@RequiredArgsConstructor
public class QkMarketingStatusServiceImpl implements IQkMarketingStatusService {

    private final QkMarketingStatusMapper  qkMarketingStatusMapper;

    /**
     * 查询所有的营销活动的详情状态  在前端下拉列表使用的
     * @return
     */
    @Override
    public List<QkMarketingStatus> getMarketingStatus() {
        return qkMarketingStatusMapper.selectList(null);
    }
}
