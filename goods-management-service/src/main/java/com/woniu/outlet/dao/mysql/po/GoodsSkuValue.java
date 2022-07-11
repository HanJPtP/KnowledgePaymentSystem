package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName goods_sku_value
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuValue implements Serializable {

    private Long id;

    private Long skuId;

    private Long valueId;

    private static final long serialVersionUID = 1L;
}