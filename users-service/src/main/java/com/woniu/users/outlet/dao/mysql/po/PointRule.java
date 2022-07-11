package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName point_rule
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointRule implements Serializable {
    /**
     * 积分规则id
     */
    @TableId("point_rule_id")
    private Long pointRuleId;

    /**
     * 商品唯一id
     */
    private Long skuId;

    /**
     * 是否有积分折扣
     */
    private Integer isDiscount;

    /**
     * 状态（1使用，0不使用）
     */
    private Integer status;

    /**
     * 可用折扣积分数（1元=100积分）
     */
    private Integer deduction;

    private static final long serialVersionUID = 1L;
}