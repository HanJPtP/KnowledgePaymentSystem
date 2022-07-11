package com.woniu.intnet.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotelViewLiveVo implements Serializable {

    private Long crlid;
    private Long userid;
    private Long num;
    private Long watchtime;

}
