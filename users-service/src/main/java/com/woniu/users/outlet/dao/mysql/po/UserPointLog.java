package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_point_log
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPointLog implements Serializable {
    /**
     * 积分日志id
     */
    @TableId("point_id")
    private Long pointId;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 积分来源
     */
    private String source;

    /**
     * 积分来源相关编号（待定）
     */
    private String referNumber;

    /**
     * 变更积分数
     */
    private Integer changePoint;

    /**
     * 积分日志生成时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}