package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_level
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLevel implements Serializable {
    /**
     * 会员级别id
     */
    @TableId("level_id")
    private Long levelId;

    /**
     * 会员级别数
     */
    private String levelNumber;

    /**
     * 会员级别名称
     */
    private String levelName;

    /**
     * 背景色
     */
    private String bgcolor;

    /**
     * 升级条件（会员等级规则外键id）
     */
    private Long roleid;

    /**
     * 升级礼包类型id(外键)
     */
    private Long giftid;

    /**
     * 该级别最低分
     */
    private Integer minPoint;

    /**
     * 该级别最高分
     */
    private Integer maxPoint;

    /**
     * 等级权益（会员权益id外键,逗号隔开）
     */
    private String rightid;

    /**
     * 等级状态
     */
    private Integer status;

    /**
     * 最后的修改时间
     */
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}