package com.woniu.users.inlet.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMsg implements Serializable {
    //用户uid
    private Long uid;

    //用户姓名
    private String username;

    //用户头像
    private String userImg;

    //用户电话号码
    private String phone;

    //用户会员等级名称
    private String levelName;

    //用户标签名称
    private String labelName;

    //用户标签名称
    private List<String> labelNameArr;

    //用户消费次数
    private Long payTimes;

    //用户消费总金额
    private Double totalPay;

    //用户状态
    private String status;

    //用户注册时间
    private String registerTime;


}
