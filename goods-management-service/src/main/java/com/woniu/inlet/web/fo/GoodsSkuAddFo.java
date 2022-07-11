package com.woniu.inlet.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuAddFo {

    private String goodsShowaddToken;
    private Long spgid;
    private String title;
    private String images;
    private String  description;
    private String  goodsdetails;
    private Integer orderno;
    private String slideShowingImgs;
    private List<GoodsSkuParamsValueAddFo> goodsSkuParamsValueList;

}
