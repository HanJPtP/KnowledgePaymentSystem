/**
 * FileName: OrderFO
 * Date:     2022/6/17 15:48
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.dto.fo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/17 15:48
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderFO implements Serializable {

    /**
     * 幂等token
     */
    private String token;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 用户帐号
     */
    private String memberUsername;

    /**
     * 优惠券id
     */
    private Long couponId;

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
     * 订单类型：0->正常订单；1->秒杀订单
     */
    private Integer orderType;

    /**
     * 物流公司(配送方式)
     */
    private String deliveryCompany;

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
     * 下单时使用的积分
     */
    private Integer useIntegration;

    /**
     * 订单详情
     */
    private List<OrderDetailFO> orderDetailFOList;
}