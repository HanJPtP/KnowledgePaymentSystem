package com.woniu.intnet.web.fo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCurrencyMsgUpdateFo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String img;

    private String name;

    private Long userid;

    private Long ccid;

    private String describes;

    private String status;

    private String cstatus;

    private String address;

    private String audition;

    private String enclosure;

    private String ordernum;

    private Double privce;

    private Long stock;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date groundingtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date offshelftime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date starttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endtime;
}
