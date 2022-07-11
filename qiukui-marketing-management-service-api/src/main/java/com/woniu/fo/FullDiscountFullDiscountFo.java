package com.woniu.fo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @TableName full_discount_full_discount
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullDiscountFullDiscountFo implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 活动名称
     */
    private String eventname;

    /**
     * 活动类型(0: 满减活动,1: 满X元包邮活动,2: 满X元满赠活动)
     */
    private Integer typeofactivity;

    /**
     * 活动内容(满减信息: 例: 满100减10)
     */
    private String activities;

    /**
     * 适用商品范围
     */
    private Integer scopeofapplication;

    /**
     * 活动状态(0:未开始,1: 进行中,2: 已结束)
     */
    private Integer activestatus;

    /**
     * 活动开始时间
     */
    private Date eventstarttime;

    /**
     * 活动结束时间
     */
    private Date eventendtime;

    private List<Integer> goodsIdList; //商品id集合


    private static final long serialVersionUID = 1L;


}