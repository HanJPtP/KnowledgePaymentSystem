package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuShowUpdateDto {

    private Long id;
    private String title;
    private String images;
    private String description;
    private String gooddetails;
    private Integer orderno;
    private Integer inventory;
    private BigDecimal price;
    private BigDecimal marketprice;
    private Integer minnum;
    private Integer maxnum;
    private String saleable;

}
