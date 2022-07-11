package com.woniu.outnet.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClassAppointment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long crlid;

    private Long userid;

    private Date appointment;

    private Date estarttime;

    private String sendornot;
    @TableField("userstatus")
    private String userStatus;

    @TableField(exist = false)
    private ClassRecordLive classRecordLive;
}
