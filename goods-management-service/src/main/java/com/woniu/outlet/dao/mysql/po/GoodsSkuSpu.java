package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName goods_sku_spu
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuSpu implements Serializable {

    private Long skuId;

    private Long spuId;

    private static final long serialVersionUID = 1L;
}