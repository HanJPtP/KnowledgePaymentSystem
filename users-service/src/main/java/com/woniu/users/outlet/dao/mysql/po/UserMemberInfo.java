package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_member_info
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMemberInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId("uid")
    private Long uid;

    /**
     * 会员级别id（外键）
     */
    private Long levelId;

    /**
     * 会员级别数
     */
    private String levelNumber;

    /**
     * 是否发放了升级礼包（1是，0否）,每次升级重置状态，发放了修改状态
     */
    private Integer giftStatus;

    /**
     * 成长值数
     */
    private Integer growthNum;

    /**
     * 积分数(抵扣规则：1元=1000积分)
     */
    private Integer points;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}