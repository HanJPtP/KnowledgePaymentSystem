package com.woniu.order.outlet.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tb_order
 */
@TableName(value ="tb_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order implements Serializable {
    /**
     * 订单id
     */
    @TableId
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 用户帐号
     */
    private String memberUsername;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 提交时间
     */
    private LocalDateTime createTime;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 运费金额
     */
    private BigDecimal freightAmount;

    /**
     * 促销优化金额（促销价、满减、阶梯价）
     */
    private BigDecimal promotionAmount;

    /**
     * 积分抵扣金额
     */
    private BigDecimal integrationAmount;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponAmount;

    /**
     * 管理员后台调整订单使用的折扣金额
     */
    private BigDecimal discountAmount;

    /**
     * 应付金额（实际支付金额）
     */
    private BigDecimal acatualPayAmount;

    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    private Integer payType;

    /**
     * 订单来源：0->PC订单；1->app订单
     */
    private Integer sourceType;

    /**
     * 订单状态：0->待付款；1->待发货；2->待收货；3->交易成功；4->已关闭；5->售后中
     */
    private Integer status;

    /**
     * 订单类型：0->正常订单；1->秒杀订单
     */
    private Integer orderType;

    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;

    /**
     * 物流单号
     */
    private String deliverySn;

    /**
     * 发票类型：0->不开发票；1->电子发票；2->纸质发票
     */
    private Integer billType;

    /**
     * 发票抬头
     */
    private String billHeader;

    /**
     * 发票内容
     */
    private String billContent;

    /**
     * 收票人电话
     */
    private String billReceiverPhone;

    /**
     * 收票人邮箱
     */
    private String billReceiverEmail;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    private String receiverPostCode;

    /**
     * 省份/直辖市
     */
    private String receiverProvince;

    /**
     * 城市
     */
    private String receiverCity;

    /**
     * 区
     */
    private String receiverRegion;

    /**
     * 详细地址
     */
    private String receiverDetailAddress;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    private Integer confirmStatus;

    /**
     * 下单时使用的积分
     */
    private Integer useIntegration;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 确认收货时间
     */
    private LocalDateTime receiveTime;

    /**
     * 评价时间
     */
    private LocalDateTime commentTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}