package com.woniu.outlet.dao.mysql.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qk
 * @since 2022-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 优惠券类型名称
     */
    @TableField("couponTypeName")
    private String couponTypeName;


}
