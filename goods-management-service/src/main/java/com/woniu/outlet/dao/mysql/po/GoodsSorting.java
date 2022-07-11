package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName goods_sorting
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "goods_sorting")
public class GoodsSorting implements Serializable {

    private Long id;

    private String name;

    private Long parentId;

    private String ifParent;

    private String isDeleted;

    @TableField(exist = false)
    private List<GoodsSorting> childrenList;

    private static final long serialVersionUID = 1L;
}