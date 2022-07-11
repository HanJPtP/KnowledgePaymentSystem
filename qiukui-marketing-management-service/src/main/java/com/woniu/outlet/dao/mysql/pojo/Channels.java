package com.woniu.outlet.dao.mysql.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Channels implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 渠道名称
     */
    @TableField("channelName")
    private String channelName;


}
