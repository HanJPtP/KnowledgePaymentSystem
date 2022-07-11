package com.woniu.intnet.web.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfigInfoVo implements Serializable {

    private String mailhost;

    private Integer mailport;

    private String mailusername;

    private String mailpassword;

    private String mailprotocol;

    private String mailfrom;

}
