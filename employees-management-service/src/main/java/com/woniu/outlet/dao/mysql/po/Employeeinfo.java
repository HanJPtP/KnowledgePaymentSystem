package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName employeeinfo
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employeeinfo implements Serializable {
    /**
     * 
     */
    private Long id;

    private String username;

    /**
     * 
     */
    private Long uid;

    /**
     * 
     */
    private String tel;

    /**
     * 
     */
    private String img;

    /**
     * 
     */
    private String vx;

    /**
     * 
     */
    private String qq;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String profile;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Date entrytime;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */
    private Date birthday;

    /**
     * 
     */
    private String author;

    /**
     * 
     */
    private String isDeleted;

    private static final long serialVersionUID = 1L;
}