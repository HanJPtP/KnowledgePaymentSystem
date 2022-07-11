package com.woniu.outlet.dao.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "goodsskudto")
public class GoodsSkuDto {

    @Id
    @Field(name = "id", type = FieldType.Long)
    private Long id;

    @Field(name = "description", type = FieldType.Text)
    private String description;

    @Field(name = "price", type = FieldType.Double)
    private BigDecimal price;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(name = "createtime", type = FieldType.Date, format = DateFormat.date, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @Field(name = "orderno", type = FieldType.Integer)
    private Integer orderno;

}
