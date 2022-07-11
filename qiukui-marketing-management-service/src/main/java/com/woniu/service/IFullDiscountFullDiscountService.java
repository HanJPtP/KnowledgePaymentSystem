package com.woniu.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.fo.FullDiscountFullDiscountFo;
import com.woniu.outlet.dao.mysql.pojo.FullDiscountFullDiscount;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;

/**
* @author QK
* @description 针对表【full_discount_full_discount】的数据库操作Service
* @createDate 2022-06-19 15:55:03
*/
public interface IFullDiscountFullDiscountService {

    //新建满X元减活动
    void addFullDiscountFullDiscount(FullDiscountFullDiscountFo fullDiscountFullDiscountFo);

    //根据状态和名称  多条件查询满减满折活动列表 并分页
    PageInfo<FullDiscountFullDiscount> getFullDiscountFullDiscountList(Integer pageNo, Integer pageSize, Integer activestatus, String eventname);
}

