package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.QkMarketingDetailsMapper;
import com.woniu.outlet.dao.mysql.pojo.QkMarketingDetails;
import com.woniu.service.IQkMarketingDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qk
 * @since 2022-06-13
 */
@Service
@Transactional
@RequiredArgsConstructor
public class QkMarketingDetailsServiceImpl  implements IQkMarketingDetailsService {

    private final QkMarketingDetailsMapper marketingDetailsMapper;
    /**
     * 新增营销活动
     * @param marketingDetails
     * @return
     */
    @Override
    public int addMarketingDetails(QkMarketingDetails marketingDetails) {
        return marketingDetailsMapper.insert(marketingDetails);
    }
}
