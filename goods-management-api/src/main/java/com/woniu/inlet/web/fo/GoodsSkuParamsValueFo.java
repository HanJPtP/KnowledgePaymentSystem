package com.woniu.inlet.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuParamsValueFo {

    private Long sppid;
    private String value;
    private BigDecimal price;
    private BigDecimal marketprice;
    private Long inventory;
    private Integer minnum;
    private Integer maxnum;
    private String saleable;
    private Date starttime;
    private Date endtime;

}
