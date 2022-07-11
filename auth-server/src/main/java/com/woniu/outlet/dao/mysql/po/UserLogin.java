package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_login
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements Serializable {

    private Long uid;

    private String account;

    private String password;

    private String status;

    private Date modifiedTime;

    private Date registerTime;

    @TableField(exist = false)
    private List<String> permsList;

    private static final long serialVersionUID = 1L;
}