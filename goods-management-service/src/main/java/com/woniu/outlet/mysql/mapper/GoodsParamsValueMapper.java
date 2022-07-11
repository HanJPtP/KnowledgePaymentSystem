package com.woniu.outlet.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.GoodsParamsValue;

/**
* @author ThinkPad
* @description 针对表【goods_params_value】的数据库操作Mapper
* @createDate 2022-06-14 19:13:33
* @Entity com.woniu.outlet.dao.mysql.po.GoodsParamsValue
*/
public interface GoodsParamsValueMapper extends BaseMapper<GoodsParamsValue> {

    default int saveGoodsParamsValueItem(GoodsParamsValue goodsParamsValue) {
        return this.insert(goodsParamsValue);
    }

}




