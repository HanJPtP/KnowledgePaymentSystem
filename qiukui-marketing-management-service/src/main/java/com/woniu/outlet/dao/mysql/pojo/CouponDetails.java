package com.woniu.outlet.dao.mysql.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 优惠券名称
     */
    @TableField("couponName")
    private String couponName;

    /**
     * 优惠券类型(0: 满减券,1: 折扣券,2: 兑换券,3: 随机金额券)
     */
    @TableField("couponTypeId")
    private Integer couponTypeId;


    /**
     * 优惠券描述
     */
    @TableField("couponDescription")
    private String couponDescription;

    /**
     * 优惠券状态(0:未开始,1: 进行中,2: 已结束)
     */
    @TableField("couponStatus")
    private Integer couponStatus;

    /**
     * 发放数量
     */
    @TableField("numberOfReleases")
    private String numberOfReleases;

    /**
     * 渠道表外键id(0:微信商城, 1: APP商城, 2: 线下店铺 )
     */
    @TableField("channelsId")
    private Integer channelsId;

    /**
     * 优惠券可用商品集合
     */
    @TableField("availableItems")
    private String availableItems;

    /**
     * 使用门槛(满多少钱)
     */
    @TableField("useThreshold")
    private Integer useThreshold;

    /**
     * 满减金额
     */
    @TableField("fullReduction")
    private Integer fullReduction;

    /**
     * 有效期
     */
    @TableField("validityPeriod")
    private String validityPeriod;

    /**
     * 领取人群(使用范围)  梅汉宙的会员等级表外键id
     */
    @TableField("user_levelId")
    private Integer userLevelid;

    /**
     * 可领取数量
     */
    @TableField("availableQuantity")
    private Integer availableQuantity;

    /**
     * 单次可使用数量
     */
    @TableField("singleUsableQuantity")
    private Integer singleUsableQuantity;

    /**
     * 分享设置 (0: 不允许分享给微信好友,1: 允许)
     */
    @TableField("shareSettings")
    private Integer shareSettings;

    /**
     * 分享文案
     */
    @TableField("shareCopy")
    private String shareCopy;

    /**
     * 转赠设置(0: 不允许转赠,1: )
     */
    @TableField("transferSettings")
    private Integer transferSettings;

    /**
     * 公开设置(0: 不允许用户自行领取 1; 允许用户领取)
     */
    @TableField("publicSettings")
    private Integer publicSettings;

    /**
     * 过期提醒(有效期表外键id)
     */
    @TableField("validityPeriodsId")
    private Integer validityPeriodsId;

    /**
     * 领取说明
     */
    @TableField("pickUpInstructions")
    private String pickUpInstructions;

    /**
     * 优惠说明
     */
    @TableField("offerDescription")
    private String offerDescription;

    /**
     * 			使用说明
     */
    @TableField("usageNotice")
    private String usageNotice;

    @Override
    public String toString() {
        return "CouponDetails{" +
                "id=" + id +
                ", couponName='" + couponName + '\'' +
                ", couponTypeId=" + couponTypeId +
                ", couponDescription='" + couponDescription + '\'' +
                ", couponStatus=" + couponStatus +
                ", numberOfReleases='" + numberOfReleases + '\'' +
                ", channelsId=" + channelsId +
                ", availableItems='" + availableItems + '\'' +
                ", useThreshold=" + useThreshold +
                ", fullReduction=" + fullReduction +
                ", validityPeriod='" + validityPeriod + '\'' +
                ", userLevelid=" + userLevelid +
                ", availableQuantity=" + availableQuantity +
                ", singleUsableQuantity=" + singleUsableQuantity +
                ", shareSettings=" + shareSettings +
                ", shareCopy='" + shareCopy + '\'' +
                ", transferSettings=" + transferSettings +
                ", publicSettings=" + publicSettings +
                ", validityPeriodsId=" + validityPeriodsId +
                ", pickUpInstructions='" + pickUpInstructions + '\'' +
                ", offerDescription='" + offerDescription + '\'' +
                ", usageNotice='" + usageNotice + '\'' +
                '}'+"\n";
    }
}
