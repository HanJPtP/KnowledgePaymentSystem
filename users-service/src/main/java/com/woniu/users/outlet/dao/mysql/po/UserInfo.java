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
 * @TableName user_info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId("uid")
    private Long uid;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户头像
     */
    private String userImg;

    /**
     * 用户性别(1男，0女)
     */
    private Integer gender;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户职业
     */
    private String job;

    /**
     * 来源方式(app...)
     */
    private String sourceWay;

    /**
     * 备注
     */
    private String note;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    /**
     *  注册日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date registerTime;

    private static final long serialVersionUID = 1L;
}