package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.users.outlet.dao.mysql.po.PointRule;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;

/**
* @author Han
* @description 针对表【point_rule】的数据库操作Mapper
* @createDate 2022-06-17 17:20:21
* @Entity com.woniu.users.outlet.dao.mysql.po.PointRule
*/
public interface PointRuleMapper extends BaseMapper<PointRule> {

    IPage<PointRule> listPointRule(IPage<PointRule> page, @Param("isDiscount") Integer isDiscount,
                                   @Param("status") Integer status);

    /**
     *  新增
     * @param pointRule
     * @return
     */
    default int addPointRule(PointRule pointRule){
        return this.insert(pointRule);
    }

    /**
     *  修改
     * @param pointRule
     * @return
     */
    default int updatePointRule(PointRule pointRule){
        return this.updateById(pointRule);
    }

    /**
     *  根据条件查询是否有该数据
     * @param pointRule
     * @return
     */
    default List<PointRule> selectPointRule(PointRule pointRule){
        QueryWrapper<PointRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", pointRule.getSkuId())
                .eq("is_discount", pointRule.getIsDiscount());
        return this.selectList(queryWrapper);
    }

    /**
     *  根据id查询
     * @param id
     * @return
     */
    default PointRule getPointRuleById(Long id){
        return this.selectById(id);
    }

    /**
     *  根据skuId查询对象,有折扣和使用中的对象
     * @param skuId
     * @return
     */
    default PointRule getBySkuId(Long skuId){
        QueryWrapper<PointRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", skuId).eq("is_discount", 1)
                .eq("status", 1);
        return this.selectOne(queryWrapper);
    }

}




