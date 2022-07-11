package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginToUserDto {

    private Date registerTime;
    private String status;

}
