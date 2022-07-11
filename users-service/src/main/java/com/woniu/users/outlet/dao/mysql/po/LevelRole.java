package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName level_role
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelRole implements Serializable {
    /**
     * 规则id
     */
    @TableId("roleid")
    private Long roleid;

    /**
     * 是否注册会员(1是，0否)
     */
    private Integer ismember;

    /**
     * 累计消费(元)
     */
    private Double money;

    /**
     * 成长值
     */
    private Integer growthNum;

    private static final long serialVersionUID = 1L;
}