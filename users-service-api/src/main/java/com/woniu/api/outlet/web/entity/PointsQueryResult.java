/**
 * FileName: CouponQueryResult
 * Date:     2022/6/17 11:48
 * Author: YuanXQ
 * Description:
 */
package com.woniu.api.outlet.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 积分查询结果
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/17 11:48
 * @since 1.0.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointsQueryResult {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     *  使用总积分数
     */
    private Integer totalPoints;


    /**
     * 优惠总金额
     */
    private Double sumDiscount;

    /**
     * 该笔订单可产生的积分
     */
    private Integer getPoint;
}