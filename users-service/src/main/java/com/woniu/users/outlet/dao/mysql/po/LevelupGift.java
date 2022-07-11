package com.woniu.users.outlet.dao.mysql.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName levelup_gift
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelupGift implements Serializable {
    /**
     * 礼包类型id
     */
    @TableId("gift_id")
    private Long giftId;

    /**
     * 优惠券id
     */
    private Long couponid;

    /**
     * 送积分数
     */
    private Integer points;

    private static final long serialVersionUID = 1L;
}