package com.woniu.outlet.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.GoodsSorting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ThinkPad
* @description 针对表【goods_sorting】的数据库操作Mapper
* @createDate 2022-06-13 16:11:40
* @Entity com.woniu.outlet.dao.mysql.po.GoodsSorting
*/
public interface GoodsSortingMapper extends BaseMapper<GoodsSorting> {

    default List<GoodsSorting> listAllGoodsSorting() {
        return this.selectList(null);
    }

    GoodsSorting getGoodsSortingByGoodsSkuId(@Param("id") Long id);

}




