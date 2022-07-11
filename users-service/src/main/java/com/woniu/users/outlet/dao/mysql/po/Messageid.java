package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName messageid
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Messageid implements Serializable {
    /**
     * 
     */
    @TableId("messageId")
    private Long messageid;

    private static final long serialVersionUID = 1L;
}