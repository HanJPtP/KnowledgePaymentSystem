package com.woniu.message.outlet.dao.mysql.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tb_message
 */
@TableName(value ="tb_message")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message implements Serializable {
    /**
     * 消息主键
     */
    @TableId
    private Long id;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由
     */
    private String routingKey;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 重试次数 (默认10次)
     */
    private Integer retryCount;

    /**
     * 消息发送失败时联系邮箱
     */
    private String contactMail;

    /**
     * 消息状态 (0: 已失败, 1: 发送中, 2: 已发送, 3:已接收)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}