package com.woniu.outnet.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.woniu.intnet.web.fo.ClassCurrencyMsgAddFo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "classcurrencymsg")
public class ClassCurrencyMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Field(name = "id",type = FieldType.Long)
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    @Field(name="img",type = FieldType.Keyword)
    private String img;
    @Field(name="name",type = FieldType.Text,analyzer = "ik_smart")
    private String name;
    @Field(name="userid",type = FieldType.Long)
    private Long userid;
    @Field(name="ccid",type = FieldType.Long)
    private Long ccid;
    @Field(name="describe",type = FieldType.Text,analyzer = "ik_smart")
    private String describes;
    @Field(name="status",type = FieldType.Keyword)
    private String status;
    @Field(name="cstatus",type = FieldType.Keyword)
    private String cstatus;
    @Field(name="address",type = FieldType.Text,analyzer = "ik_smart")
    private String address;
    @Field(name="audition",type = FieldType.Text)
    private String audition;
    @Field(name="status",type = FieldType.Keyword)
    private String enclosure;
    @Field(name = "ordernum",type = FieldType.Keyword)
    private String ordernum;
    @Field(name = "privce",type = FieldType.Double)
    private Double privce;
    @Field(name="stock",type = FieldType.Long)
    private Long stock;
    @Field(name="groundingtime",type = FieldType.Date ,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date groundingtime;
    @Field(name="offshelftime",type = FieldType.Date ,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date offshelftime;
    @Field(name="starttime",type = FieldType.Date ,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    @Field(name="endtime",type = FieldType.Date ,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;


    public ClassCurrencyMsg(ClassCurrencyMsgAddFo classCurrencyMsgAddFo){
        this.address =classCurrencyMsgAddFo.getAddress();
        this.img=classCurrencyMsgAddFo.getImg();
        this.starttime=classCurrencyMsgAddFo.getStarttime();
        this.audition=classCurrencyMsgAddFo.getAudition();
        this.ccid=classCurrencyMsgAddFo.getCcid();
        this.describes=classCurrencyMsgAddFo.getDescribes();
        this.cstatus=classCurrencyMsgAddFo.getCstatus();
        this.enclosure=classCurrencyMsgAddFo.getEnclosure();
        this.name=classCurrencyMsgAddFo.getName();
        this.privce =classCurrencyMsgAddFo.getPrivce();
        this.stock=classCurrencyMsgAddFo.getStock();
        this.userid = classCurrencyMsgAddFo.getUserid();
        this.endtime=classCurrencyMsgAddFo.getEndtime();
    }



}
