package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.po.GoodsParamsValue;
import com.woniu.outlet.mysql.mapper.GoodsParamsValueMapper;
import com.woniu.service.IGoodsParamsValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsParamsValueServiceImpl implements IGoodsParamsValueService {

    private final GoodsParamsValueMapper goodsParamsValueMapper;

    @Override
    public int saveGoodsParamsValueItem(GoodsParamsValue goodsParamsValue) {
        return goodsParamsValueMapper.saveGoodsParamsValueItem(goodsParamsValue);
    }
}
