package com.woniu.order.outlet.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tb_cart_item
 */
@TableName(value ="tb_cart_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartItem implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 商品的id
     */
    private Long productId;

    /**
     * 商品sku的id
     */
    private Long productSkuId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 添加到购物车时的价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime modifyDate;

    /**
     * 是否删除
     */
    private Integer deleteStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}