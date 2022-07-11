package com.woniu.users.inlet.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  用户基础资料
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto implements Serializable {
    // 用户id
    private Long uid;

    // 用户姓名
    private String username;

    // 用户性别
    private Integer gender;

    // 用户头像
    private String userImg;

    // 用户年龄
    private Integer age;

    // 用户手机号
    private String phone;

    // 用户邮箱
    private String email;

    // 用户职业
    private String job;

    // 来源方式
    private String sourceWay;

    // 注册时间
    private Date registerTime;

    // 最近登录时间
    private Date latestLoginTime;

    // 备注
    private String note;

    // 常用收货人
    private String consignee;

    // 详细地址
    private String addr;
}
