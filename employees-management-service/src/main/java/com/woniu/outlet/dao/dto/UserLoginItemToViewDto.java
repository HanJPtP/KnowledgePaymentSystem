package com.woniu.outlet.dao.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserLoginItemToViewDto {

    private Long uid;
    private String account;
    private String role;
    private String status;
    private String username;
    private String author;
    private String registertime;

}
