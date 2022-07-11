package com.woniu.outlet.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.GoodsSkuValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ThinkPad
* @description 针对表【goods_sku_value】的数据库操作Mapper
* @createDate 2022-06-15 14:05:21
* @Entity com.woniu.outlet.dao.mysql.po.GoodsSkuValue
*/
public interface GoodsSkuValueMapper extends BaseMapper<GoodsSkuValue> {

    /**
     * 保存 goodsskuvalue 一个数组
     * @param goodsSkuValueList
     * @return
     */
    int saveGoodsSkuAndValueList(@Param("goodsSkuValueList") List<GoodsSkuValue> goodsSkuValueList);

    /**
     * 删除 spu_id 在该数组中的数据
     * @param goodsSkuIdList
     * @return
     */
    default int removeGoodsSkuValueListByGoodsSkuIds(List<Long> goodsSkuIdList) {
        QueryWrapper<GoodsSkuValue> wrapper = new QueryWrapper<GoodsSkuValue>()
                .in("sku_id", goodsSkuIdList);
        return this.delete(wrapper);
    }

}




