/**
 * FileName: OrderDetailFO
 * Date:     2022/6/20 16:24
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

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/20 16:24
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailFO implements Serializable {

    /**
     * 商品sku id
     */
    private Long productSkuId;

    /**
     * 商品图片
     */
    private String productPic;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 销售价格
     */
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    private Integer productQuantity;
}