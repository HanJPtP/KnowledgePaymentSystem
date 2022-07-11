package com.woniu.inlet.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginUpdateFo {

    private String token;
    private Long uid;
    private Long eid;
    private String oldaccount;
    private String account;
    private String username;
    private String tel;
    private String img;
    private Long role;
    private String oldpassword;
    private String newpassword;
    private String vx;
    private String qq;
    private String profile;
    private String email;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

}
