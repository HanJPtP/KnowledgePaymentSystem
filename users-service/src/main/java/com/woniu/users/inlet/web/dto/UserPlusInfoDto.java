package com.woniu.users.inlet.web.dto;

import com.woniu.users.outlet.dao.mysql.po.MemberPlusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  付费用户会员信息类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPlusInfoDto implements Serializable {
    /**
     * 用户id
     */
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

    /**
     *  付费会员类型
     */
    private MemberPlusType memberPlusType;

}
