package com.woniu.outlet.dao.mysql.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author qk
 * @since 2022-06-16-15:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CouponUserDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 优惠券类型(0: 满减券,1: 折扣券,2: 兑换券,3: 随机金额券)
     */
    @TableField("couponTypeId")
    private Integer couponTypeId;
    /**
     *优惠券详情表外键id
     */
    @TableField("coupondetailsId")
    private Long coupondetailsId;
    /**
     * 领取用户id
     */
    @TableField("usersId")
    private Long usersId;
    /**
     *领取时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("pickUpTime")
    private LocalDateTime pickUpTime;
    /**
     *优惠券使用状态(0:未使用,1: 已使用,2: 已过期)
     */
    @TableField("couponUsageStatus")
    private int couponUsageStatus;
    /**
     * 用户邮箱
     */
    @TableField("userEmail")
    private String userEmail;

    /**
     * 使用时间
     */
    @TableField("usageTime")
    private String usageTime;



}
