/**
 * FileName: CouponInfoToEventCenter
 * Date:     2022/6/17 11:29
 * Author: YuanXQ
 * Description:
 */
package com.woniu.api.outlet.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 可能用到的订单信息
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointsInfoToUserCenter {
    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 订单id
     */
    private Long orderId;


    /**
     * 商品列表
     */
    private List<ProductInfoByOrderCenter> productList;

    /**
     * 商品总价
     */
    private Double totalAmount;

}