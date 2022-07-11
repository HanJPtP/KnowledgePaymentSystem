package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_pay_log
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayLog implements Serializable {
    /**
     * 消费日志id
     */
    @TableId("pay_id")
    private Long payId;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 记录来源
     */
    private String source;

    /**
     * 相关单据id
     */
    private String sourceSn;

    /**
     * 记录生成时间
     */
    private Date createTime;

    /**
     * 变动金额
     */
    private Double amount;

    private static final long serialVersionUID = 1L;
}