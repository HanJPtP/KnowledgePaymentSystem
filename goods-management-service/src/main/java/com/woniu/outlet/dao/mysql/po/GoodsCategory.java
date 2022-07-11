package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName goods_category
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCategory implements Serializable {

    private Long id;

    private Long spgId;

    private String name;

    private String isDeleted;

    private static final long serialVersionUID = 1L;
}