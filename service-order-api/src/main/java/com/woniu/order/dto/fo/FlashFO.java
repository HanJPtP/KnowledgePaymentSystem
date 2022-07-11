/**
 * FileName: FlashFO
 * Date:     2022/6/22 18:10
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
 * @date 2022/6/22 18:10
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlashFO implements Serializable {

    private Long memberId;
    private Long productSkuId;
    private String productName;
    private String productPic;
    private BigDecimal price;
    private BigDecimal actualPayAmount;
}