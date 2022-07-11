package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_addr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddr implements Serializable {
    /**
     * 主键id
     */
    @TableId("addr_id")
    private Long addrId;

    /**
     * 用户uid
     */
    private Long uid;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 具体门牌号
     */
    private String address;

    /**
     * 是否默认(1是，0否)
     */
    private Integer isDefault;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;
}