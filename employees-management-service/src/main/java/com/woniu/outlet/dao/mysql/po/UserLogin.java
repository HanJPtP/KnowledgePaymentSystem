package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * 
 * @TableName user_login
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements Serializable {
    /**
     * 
     */
    @TableId(value = "uid")
    private Long uid;

    /**
     * 
     */
    private String account;

    /**
     * 
     */
    private String password;

    /**
     * 0 正常, 1 锁定不能登录, 2 已删除
     */
    private String status;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 注册时间
     */
    private Date registerTime;

    private static final long serialVersionUID = 1L;
}