package com.woniu.intnet.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassCurrencyMsgAddFo implements Serializable {

    private String name;

    private String img;

    private Long ccid;

    private Long userid;

    private String describes;

    private String cstatus;

    private String address;

    private String audition;

    private String enclosure;

    private Double privce;

    private Long stock;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date starttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endtime;

    private String restFul;
}
