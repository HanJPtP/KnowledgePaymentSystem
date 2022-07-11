package com.woniu.order.outlet.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tb_order_operate_history
 */
@TableName(value ="tb_order_operate_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderOperateHistory implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 操作人：用户；系统；后台管理员
     */
    private String operateMan;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;

    /**
     * 操作类型：0->关闭订单; 1->付款操作; 2->发货操作; 3->收货操作; 4->完成订单; 5->申请售后; 
     */
    private Integer operationType;

    /**
     * 备注
     */
    private String note;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}