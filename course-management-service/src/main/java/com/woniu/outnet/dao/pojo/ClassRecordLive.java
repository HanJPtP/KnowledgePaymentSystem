package com.woniu.outnet.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.woniu.intnet.web.fo.ClassRecordLiveUpdateFo;
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
@Document(indexName = "classrecordlive")
public class ClassRecordLive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @TableId(value = "id", type = IdType.INPUT)
    @Field(name = "id", type = FieldType.Long)
    private Long id;
    @Field(name = "userid", type = FieldType.Long)
    private Long userid;
    @Field(name = "name", type = FieldType.Keyword, analyzer = "ik_smart")
    private String name;
    @Field(name = "img", type = FieldType.Keyword)
    private String img;
    @Field(name = "liveStatus", type = FieldType.Keyword)
    private String liveStatus;
    @Field(name = "num", type = FieldType.Long)
    private Long num;
    @Field(name = "status", type = FieldType.Keyword)
    private String status;
    @Field(name = "starttime", type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    @Field(name = "endtime", type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    @Field(name = "estarttime", type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date estarttime;
    @Field(name = "ordernum", type = FieldType.Keyword)
    private String ordernum;


    public ClassRecordLive(ClassRecordLiveUpdateFo classRecordLiveUpdateFo) {
        this.id = classRecordLiveUpdateFo.getId();
        this.img = classRecordLiveUpdateFo.getImg();
        this.name = classRecordLiveUpdateFo.getName();
        this.userid = classRecordLiveUpdateFo.getUserid();
        this.endtime=classRecordLiveUpdateFo.getEndtime();
        this.estarttime=classRecordLiveUpdateFo.getEstarttime();
        this.liveStatus = classRecordLiveUpdateFo.getLiveStatus();
        this.num=classRecordLiveUpdateFo.getNum();
        this.ordernum=classRecordLiveUpdateFo.getOrdernum();
        this.status=classRecordLiveUpdateFo.getStatus();
        this.starttime=classRecordLiveUpdateFo.getStarttime();
    }
}
