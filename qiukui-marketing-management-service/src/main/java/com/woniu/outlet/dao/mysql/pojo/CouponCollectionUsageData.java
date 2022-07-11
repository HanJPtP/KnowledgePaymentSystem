package com.woniu.outlet.dao.mysql.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * 优惠券领取使用数据表
 * @TableName coupon_collection_usage_data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CouponCollectionUsageData implements Serializable {

    private Long id;

    /**
     * 优惠券类型(0: 满减券,1: 折扣券,2: 兑换券,3: 随机金额券)
     */
    @TableField("coupontypeid")
    private Integer coupontypeid;

    /**
     * 优惠券详情表外键id
     */
    @TableField("couponDetailsId")
    private Long couponDetailsId;

    /**
     * 领取张数
     */
    @TableField("numberofsheetsreceived")
    private Integer numberofsheetsreceived;

    /**
     * 领取人数
     */
    @TableField("numberofrecipients")
    private Integer numberofrecipients;

    /**
     * 使用张数
     */
    @TableField("numberofsheetsused")
    private Integer numberofsheetsused;

    /**
     * 使用人数
     */
    @TableField("numberofusers")
    private Integer numberofusers;

    /**
     * 当前时间
     */
    @TableField("currenttime")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date currenttime;

    private static final long serialVersionUID = 1L;

}