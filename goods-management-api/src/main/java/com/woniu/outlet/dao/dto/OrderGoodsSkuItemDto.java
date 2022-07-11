package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoodsSkuItemDto {

    private Long id;
    private String inventory;
    private BigDecimal price;
    private String images;

    private List<ParamsValueDto> paramsValueDtoList;

}
