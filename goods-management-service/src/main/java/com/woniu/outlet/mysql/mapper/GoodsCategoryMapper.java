package com.woniu.outlet.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.dto.CategoryToParamsToValueDto;
import com.woniu.outlet.dao.dto.ValueAndIdDto;
import com.woniu.outlet.dao.mysql.po.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ThinkPad
* @description 针对表【goods_category】的数据库操作Mapper
* @createDate 2022-06-14 15:25:54
* @Entity com.woniu.outlet.dao.mysql.po.GoodsCategory
*/
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {

    /**
     * 通过商品分类级别 ID 获得该分类级别下所有参数以及参数值信息
     * @param id
     * @return
     */
    List<CategoryToParamsToValueDto> listAllParamsToValuesById(Long id);

    /**
     * 通过参数 ID 获得所有对应的参数值信息
     * @param id
     * @return
     */
    List<ValueAndIdDto> listAllValuesByParamId(Long id);

    /**
     * 通过商品 goods_sku ID 获得对应的所有的参数以及对应参数值
     * @param skuid
     * @return
     */
    List<CategoryToParamsToValueDto> listGoodsSkuParamsAndValueByGoodsSkuId(@Param("skuid") Long skuid);

}




