package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName goods_spu
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "goods_spu")
public class GoodsSpu implements Serializable {

    private Long id;

    private String title;

    private String subTitle;

    private Integer categoryId;

    private Integer spgId;

    private String saleable;

    private String valid;

    private Date createTime;

    private Date lastUpdateTime;

    private String isDeleted;

    private static final long serialVersionUID = 1L;
}