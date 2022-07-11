package com.woniu.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.fo.FullDiscountFullDiscountFo;
import com.woniu.outlet.dao.mysql.FullDiscountFullDiscountMapper;
import com.woniu.outlet.dao.mysql.pojo.FullDiscountFullDiscount;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;
import com.woniu.service.IFullDiscountFullDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author QK
* @description 针对表【full_discount_full_discount】的数据库操作Service实现
* @createDate 2022-06-19 15:55:03
*/
@Service
@Transactional
@RequiredArgsConstructor
public class FullDiscountFullDiscountServiceImpl implements IFullDiscountFullDiscountService {

    private final FullDiscountFullDiscountMapper fullDiscountFullDiscountMapper;
    /**
     * 新建满X元减活动
     * @param fullDiscountFullDiscountFo
     * @return
     */
    @Override
    public void addFullDiscountFullDiscount(FullDiscountFullDiscountFo fullDiscountFullDiscountFo) {
        //将fo 转成  po
        FullDiscountFullDiscount fullDiscountFullDiscount = FullDiscountFullDiscount.builder()
                .eventname(fullDiscountFullDiscountFo.getEventname())
                .typeofactivity(fullDiscountFullDiscountFo.getTypeofactivity())
                .activities(fullDiscountFullDiscountFo.getActivities())
                .scopeofapplication(fullDiscountFullDiscountFo.getScopeofapplication())
                .activestatus(fullDiscountFullDiscountFo.getActivestatus())
                .eventstarttime(fullDiscountFullDiscountFo.getEventstarttime())
                .eventendtime(fullDiscountFullDiscountFo.getEventendtime())
                .build();

        //新建满X元减活动
        fullDiscountFullDiscountMapper.insert(fullDiscountFullDiscount);

        List<Integer> goodsIdList = fullDiscountFullDiscountFo.getGoodsIdList();



        //刚生成的新建满X元减活动的id
        Long fullDiscountFullDiscountId = fullDiscountFullDiscount.getId();
        //给中间表  绑定新建的活动和商品
        for (int i = 0; i < goodsIdList.size(); i++) {
            fullDiscountFullDiscountMapper.addItemsEligibleForFullDiscount(fullDiscountFullDiscountId,goodsIdList.get(i).longValue());
        }



    }

    /**
     * 根据状态和名称  多条件查询满减满折活动列表 并分页
     * @param pageNo
     * @param pageSize
     * @param activestatus
     * @param eventname
     * @return
     */
    @Override
    public PageInfo<FullDiscountFullDiscount> getFullDiscountFullDiscountList(Integer pageNo, Integer pageSize, Integer activestatus, String eventname) {
        Page<FullDiscountFullDiscount> page = new Page<>(pageNo,pageSize);

        Page<FullDiscountFullDiscount> fullDiscountFullDiscountList = fullDiscountFullDiscountMapper.getFullDiscountFullDiscountList(page, activestatus, eventname);
        PageInfo<FullDiscountFullDiscount> pageInfo = new PageInfo<>(fullDiscountFullDiscountList);
        return pageInfo;
    }
}
