package com.woniu.outlet.dao.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoodsSkuItemDto {

    private Long id;
    private String inventory;
    private BigDecimal price;
    private String images;

    @TableField(exist = false)
    private List<ParamsValueDto> paramsValueDtoList;

}
