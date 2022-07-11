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
public class WatchUserTimeByLiveVo implements Serializable {
    private Long id;

    private Long useridPlay;

    private String name;

    private String img;

    private String liveStatus;

    private Long num;

    private String status;

    private Date starttime;

    private Date endtime;

    private Date estarttime;

    private String ordernum;

    private Long userid;

    private Long watchtime;
}
