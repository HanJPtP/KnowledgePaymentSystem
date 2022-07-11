package com.woniu.outlet.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.GoodsSku;
import com.woniu.outlet.dao.mysql.po.GoodsSkuSpu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ThinkPad
* @description 针对表【goods_sku_spu】的数据库操作Mapper
* @createDate 2022-06-15 14:03:47
* @Entity com.woniu.outlet.dao.mysql.po.GoodsSkuSpu
*/
public interface GoodsSkuSpuMapper extends BaseMapper<GoodsSkuSpu> {

    /**
     * 添加 goods_sku_spu 数组, 通过 spg_id 获得对应的 spu_id
     * @param goodsSkuIdList
     * @param spgid
     * @return
     */
    int saveGoodsSkuSpuList(@Param("goodsSkuIdList") List<Long> goodsSkuIdList,
                            @Param("spgid") Long spgid);

    /**
     * 删除在 sku_id 在该数组中的所有数据
     * @param goodsSkuIdList
     * @return
     */
    default int removeGoodsSkuSpuList(List<Long> goodsSkuIdList) {
        QueryWrapper<GoodsSkuSpu> qw = new QueryWrapper<GoodsSkuSpu>()
                .in("sku_id", goodsSkuIdList);
        return this.delete(qw);
    }

}




