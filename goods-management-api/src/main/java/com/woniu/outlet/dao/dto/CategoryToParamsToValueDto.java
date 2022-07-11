package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 指定类别的所有参数, 以及参数的所有参数值
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryToParamsToValueDto {

    private Long id;

    private String params;

    private List<String>  valueList;

}
