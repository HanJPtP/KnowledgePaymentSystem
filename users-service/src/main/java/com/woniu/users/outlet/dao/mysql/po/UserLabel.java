package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_label
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLabel implements Serializable {
    /**
     * 标签id
     */
    @TableId("label_id")
    private Long labelId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 一级归类
     */
    private String labelRank;

    /**
     * 标签类型
     */
    private String labelType;

    /**
     *  标签状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}