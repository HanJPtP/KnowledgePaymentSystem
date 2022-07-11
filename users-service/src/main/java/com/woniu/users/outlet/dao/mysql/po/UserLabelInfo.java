package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.NonLeaked;

/**
 * 
 * @TableName user_label_info
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLabelInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId("uid")
    private Long uid;

    /**
     * 标签id
     */
    private Long labelId;

    private static final long serialVersionUID = 1L;
}