package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuAddDto {

    private Long id;
    private String title;
    private String images;
    private String slideshowimgs;
    private Long sortingid;
    private String  description;
    private String  goodsdetails;
    private Integer orderno;


}
