package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserOneItemToUpdateDto {

    private Long eid;
    private Long uid;
    private String username;
    private String vx;
    private String qq;
    private String tel;
    private String email;
    private String account;
    private String password;

}
