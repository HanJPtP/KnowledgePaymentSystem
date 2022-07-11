package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName role
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String role;

    /**
     * 
     */
    private String name;

    private static final long serialVersionUID = 1L;
}