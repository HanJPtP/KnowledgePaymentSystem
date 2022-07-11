package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @TableName goods_params_value
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsParamsValue implements Serializable {

    private Long id;

    private Long sppId;

    private String value;

    private static final long serialVersionUID = 1L;
}