package com.woniu.users.inlet.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  用户标签 信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelInfoDto implements Serializable {

    /**
     * 标签id
     */
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

    // 同个标签用户数量
    private Integer userNum;
}
