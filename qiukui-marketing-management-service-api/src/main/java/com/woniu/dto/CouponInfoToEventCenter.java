/**
 * FileName: CouponInfoToEventCenter
 * Date:     2022/6/17 11:29
 * Author: YuanXQ
 * Description:
 */
package com.woniu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 可能用到的订单信息
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/17 11:29
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponInfoToEventCenter {
    /**
     * 会员id
     */
    private Long memberId;

//    /**
//     * 订单id
//     */
//    private Long orderId;

//    /**
//     * 订单号
//     */
//    private String orderSn;

//    /**
//     * 商品列表
//     */
//    private List<ProductInfoByOrderCenter> productList;

    /**
     * 商品总价
     */
    private BigDecimal totalAmount;

    /**
     * 优惠券id
     */
    private Long couponId;
}