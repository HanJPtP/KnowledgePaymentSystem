package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName member_rights
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRights implements Serializable {
    /**
     * 权益id
     */
    @TableId("right_id")
    private Long rightId;

    /**
     * 权益名称
     */
    private String rightName;

    /**
     * 权益图标
     */
    private String rightImg;

    /**
     * 权益说明
     */
    private String rightInfo;

    /**
     * 权益状态（1启用，0禁用）
     */
    private Integer status;

    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}