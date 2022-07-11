package com.woniu.outlet.dao.dto;

/**
 * 定义订单中存入的  属性 : 属性值 map
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParamsValueDto {

    private String key;
    private String value;

}
