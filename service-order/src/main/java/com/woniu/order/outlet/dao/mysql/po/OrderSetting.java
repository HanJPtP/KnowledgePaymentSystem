package com.woniu.order.outlet.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tb_order_setting
 */
@TableName(value ="tb_order_setting")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderSetting implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 秒杀订单超时关闭时间(分)
     */
    private Integer flashOrderOvertime;

    /**
     * 正常订单超时时间(分)
     */
    private Integer normalOrderOvertime;

    /**
     * 发货后自动确认收货时间（天）
     */
    private Integer confirmOvertime;

    /**
     * 自动完成交易时间，不能申请售后（天）
     */
    private Integer finishOvertime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}