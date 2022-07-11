package com.woniu.inlet.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfoUpdateOneFo {

    private String token;
    private Long uid;
    private Long eid;
    private String username;
    private String vx;
    private String qq;
    private String tel;
    private String email;
    private String account;
    private String oldpassword;
    private String newpassword;

}
