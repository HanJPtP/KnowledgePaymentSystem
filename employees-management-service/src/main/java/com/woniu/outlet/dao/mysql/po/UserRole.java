package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName user_role
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    /**
     * 
     */
    private Long uid;

    /**
     * 
     */
    private Long roleid;

    private static final long serialVersionUID = 1L;
}