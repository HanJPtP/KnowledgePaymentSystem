package com.woniu.outlet.dao.mysql.pojo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qk
 * @since 2022-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QkMarketingType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 营销类型名称	
     */
    private String marketingName;


}
