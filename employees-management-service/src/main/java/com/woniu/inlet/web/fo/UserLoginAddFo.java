package com.woniu.inlet.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginAddFo {

    private String token;
    private String account;
    private String username;
    private String password;
    private String status;
    private String tel;
    private String img;
    private String vx;
    private String qq;
    private String email;
    private String profile;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private Long role;

}
