package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_plus_info
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPlusInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId("uid")
    private Long uid;

    /**
     * 付费会员类型id(外键）
     */
    private Long plusTypeid;

    /**
     * 是否有续卡提醒(0不提醒，1提醒)
     */
    private Integer cardRemind;

    /**
     * 开卡时间
     */
    private Date openTime;

    /**
     * 用户会员状态（已过期，未过期）
     */
    private String status;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}