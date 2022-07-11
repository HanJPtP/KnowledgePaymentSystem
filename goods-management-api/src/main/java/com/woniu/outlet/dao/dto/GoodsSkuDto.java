package com.woniu.outlet.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class GoodsSkuDto {

    private Long id;
    private String description;
    private BigDecimal price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    private Integer orderno;

}
