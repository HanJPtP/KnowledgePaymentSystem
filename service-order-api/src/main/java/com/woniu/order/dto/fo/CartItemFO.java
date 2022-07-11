/**
 * FileName: CartItemDTO
 * Date:     2022/6/17 9:56
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
 * @date 2022/6/17 9:56
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemFO implements Serializable {

    private Long productId;
    private Long productSkuId;
    private Long memberId;
    private Integer quantity;
    private BigDecimal price;

}