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
 * @TableName member_plus_type
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPlusType implements Serializable {
    /**
     * 付费类型id
     */
    @TableId("plus_typeid")
    private Long plusTypeid;

    /**
     * 会员卡名
     */
    private String plusname;

    /**
     * 背景色
     */
    private String bgcolor;

    /**
     * 有效时长（月）
     */
    private Integer efficientTime;

    /**
     * 价格
     */
    private Double price;

    /**
     * 特权描述
     */
    private String description;

    /**
     * 类型状态
     */
    private Integer status;

    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}