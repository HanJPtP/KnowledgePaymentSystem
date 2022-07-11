package com.woniu.outlet.dao.mysql;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outlet.dao.mysql.pojo.FullDiscountFullDiscount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
* @author QK
* @description 针对表【full_discount_full_discount】的数据库操作Mapper
* @createDate 2022-06-19 15:55:03
* @Entity generator.com/woniu/outlet/dao/mysql.FullDiscountFullDiscountFo
*/
public interface FullDiscountFullDiscountMapper extends BaseMapper<FullDiscountFullDiscount> {

    //根据状态和名称  多条件查询满减满折活动列表 并分页
   default Page<FullDiscountFullDiscount> getFullDiscountFullDiscountList(Page<FullDiscountFullDiscount> page,Integer activestatus,String eventname){
       QueryWrapper<FullDiscountFullDiscount> queryWrapper = new QueryWrapper<FullDiscountFullDiscount>();
       if (activestatus > 0){
           queryWrapper.eq("activestatus",activestatus);
       }
       if (eventname != null && eventname.length() > 0){
           queryWrapper.like("eventname",eventname);
       }
       return this.selectPage(page,queryWrapper);
   }

   //给 中间表 满减活动适用商品表:  items_eligible_for_full_discount 添加数据
    @Insert("insert into items_eligible_for_full_discount (fullDiscountFullDiscountId,goodsId) values (#{fullDiscountFullDiscountId},#{goodsId})")
    int addItemsEligibleForFullDiscount(@Param("fullDiscountFullDiscountId") Long fullDiscountFullDiscountId,@Param("goodsId") Long goodsId);

}
