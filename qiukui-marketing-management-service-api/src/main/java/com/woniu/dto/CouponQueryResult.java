/**
 * FileName: CouponQueryResult
 * Date:     2022/6/17 11:48
 * Author: YuanXQ
 * Description:
 */
package com.woniu.dto;

import java.math.BigDecimal;

/**
 * <p>
 * 优惠券查询结果
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/17 11:48
 * @since 1.0.0
 */
public class CouponQueryResult {

    /**
     * 优惠券优惠价格
     */
    private BigDecimal disCountAmount;

    /**
     * 优惠券使用后总价
     */
    private BigDecimal fixedAmount;
}