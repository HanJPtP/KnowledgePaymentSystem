package com.woniu.intnet.web.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WatchUserTimeByCurrencyVo implements Serializable {
    private Long id;

    private Long crlid;

    private Long userid;

    private Long watchtime;

    private String img;

    private String name;

    private Long userPlayid;

    private Long ccid;

    private String describe;

    private String status;

    private String cstatus;

    private String address;

    private String audition;

    private String enclosure;

    private String ordernum;

    private Double privce;

    private Long stock;

    private Date groundingtime;

    private Date offshelftime;

    private Date starttime;

    private Date endtime;
}
