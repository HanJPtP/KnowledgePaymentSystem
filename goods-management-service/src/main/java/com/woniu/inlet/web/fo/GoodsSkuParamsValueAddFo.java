package com.woniu.inlet.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuParamsValueAddFo {

    private String sppids; // 所有参数 ID
    private String valueids; // 所有参数值 ID
    private BigDecimal price;
    private BigDecimal marketprice;
    private Long inventory;
    private Integer minnum;
    private Integer maxnum;
    private String saleable;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;

}
