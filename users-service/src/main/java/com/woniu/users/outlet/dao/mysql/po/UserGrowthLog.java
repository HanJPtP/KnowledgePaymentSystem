package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_growth_log
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGrowthLog implements Serializable {
    /**
     * 成长值日志id
     */
    @TableId("growth_id")
    private Long growthId;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 成长值来源
     */
    private String source;

    /**
     * 成长值来源相关编号（待定）
     */
    private String referNumber;

    /**
     * 变更成长值数
     */
    private Integer changePoint;

    /**
     * 成长值日志生成时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}