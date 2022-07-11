package com.woniu.users.inlet.web.dto;

import com.woniu.users.outlet.dao.mysql.po.UserLearnMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *  用户行为统计表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBehaviorDto implements Serializable {
    // 用户uid
    private Long uid;

    // 下单数量
    private Integer userPayTimes;

    // 下单金额
    private Double userTotalPay;

    // 最近消费时间
    private Date currentTime;

    //  用户学习记录信息对象
    private UserLearnMsg userLearnMsg;
}
