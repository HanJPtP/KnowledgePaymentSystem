package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName goods_sku_slideShowingImgs
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods_sku_slideShowingImgs")
public class GoodsSkuSlideshowingimgs implements Serializable {

    private Long skuId;

    private String imgurl;

    private static final long serialVersionUID = 1L;
}