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
public class QkMarketingStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 活动状态(未开始,进行中,已结束)
     */
    private String activeStatus;

    @Override
    public String toString() {
        return "QkMarketingStatus{" +
                "id=" + id +
                ", activeStatus='" + activeStatus + '\'' +
                '}'+ "\n";
    }
}
