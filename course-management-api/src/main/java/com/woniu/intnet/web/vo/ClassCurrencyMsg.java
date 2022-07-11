package com.woniu.intnet.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

public class ClassCurrencyMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String img;

    private String name;

    private Long userid;

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
