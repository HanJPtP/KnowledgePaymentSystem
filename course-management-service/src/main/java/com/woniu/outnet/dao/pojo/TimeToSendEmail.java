package com.woniu.outnet.dao.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TimeToSendEmail implements Serializable {

    private String id;

    private String crlid;

    private Long userid;
    @TableField("userplayid")
    private String userPlayid;

    private String estarttime;
    @TableField("sendornot")
    private String sendOrNot;

    private String name;
}
