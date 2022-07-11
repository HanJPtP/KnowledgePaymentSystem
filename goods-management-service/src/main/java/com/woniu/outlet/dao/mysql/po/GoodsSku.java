
package com.woniu.outlet.dao.mysql.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
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

/**
 * 
 * @TableName goods_sku
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "goods_sku")
@Document(indexName = "goodssku")
public class GoodsSku implements Serializable {

    @Id
    @Field(name = "id", type = FieldType.Long)
    private Long id;

    @Field(name = "title", type = FieldType.Text)
    private String title;

    @Field(name = "inventory", type = FieldType.Long)
    private Long inventory;

    @Field(name = "images", type = FieldType.Keyword)
    private String images;

    @Field(name = "description", type = FieldType.Text)
    private String description;

    @Field(name = "gooddetails", type = FieldType.Text)
    private String gooddetails;

    @Field(name = "price", type = FieldType.Double)
    private BigDecimal price;

    @Field(name = "marketprice", type = FieldType.Double)
    private BigDecimal marketprice;

    @Field(name = "orderno", type = FieldType.Integer)
    private Integer orderno;

    @Field(name = "minnum", type = FieldType.Integer)
    private Integer minnum;

    @Field(name = "maxnum", type = FieldType.Integer)
    private Integer maxnum;

    @Field(name = "saleable", type = FieldType.Text)
    private String saleable;

    @Field(name = "valid", type = FieldType.Text)
    private String valid;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(name = "createTime", type = FieldType.Date, format = DateFormat.date, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(name = "lastUpdateTime", type = FieldType.Date, format = DateFormat.date, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    @Field(name = "isDeleted", type = FieldType.Text)
    private String isDeleted;

    private static final long serialVersionUID = 1L;
}