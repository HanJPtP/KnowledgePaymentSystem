/**
 * FileName: ProductInfoByOrderCenter
 * Date:     2022/6/17 11:34
 * Author: YuanXQ
 * Description:
 */
package com.woniu.api.outlet.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 可能用到的商品信息
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/17 11:34
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoByOrderCenter {

    /**
     * SKU id
     */
    private Long skuId;

    /**
     * SPU id
     */
    private Long spuId;

    /**
     * 商品数量
     */
    private Integer quantity;
}