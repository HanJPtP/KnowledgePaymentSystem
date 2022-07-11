package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_coupon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCoupon implements Serializable {
    /**
     * 用户id
     */
    @TableId("uid")
    private Long uid;

    /**
     * 优惠券id
     */
    private Long couponid;

    /**
     * 优惠券状态（是否过期）
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}