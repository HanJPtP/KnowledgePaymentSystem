package com.woniu.inlet.web.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 优惠券详情表
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠券类型名称
     */
    private Integer couponType;

    /**
     * 使用范围
     */
    @TableField("availableItems")
    private String availableItems;


    /**
     * 优惠券状态(0:未开始,1: 进行中,2: 已结束)
     */
    @TableField("couponStatus")
    private Integer couponStatus;

    /**
     * 有效期
     */
    @TableField("validityPeriod")
    private String validityPeriod;



}
