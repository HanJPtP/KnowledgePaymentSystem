package com.woniu.inlet.web.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author qk
 * @since 2022-06-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QkMarketingDetailsFO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 营销活动类型外键id
     */
    private Long marketingTypeId;

    /**
     * 活动时间(0: 活动当天,1: 活动当周,2: 活动当月)
     */
    private Integer activityTime;

    /**
     * 会员范围(0: 全部会员,1: 会员等级)
     */
    private String memberShip;

    /**
     * 活动状态表外键id
     */
    private Long activeStatusId;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime addTime;

    /**
     * 活动渠道(0: 微信商城,1: 线下店铺, 2: app商城)
     */
    private String activityChannel;

    /**
     * 活动规则(0: 消费折扣,1: 积分加倍, 2: 赠送优惠券,3: 包邮)
     */
    private String ruleOfActivity;

    /**
     * 通知渠道(0: 短信, 1: 商城消息推送)
     */
    private String notificationChannel;

    /**
     * 通知时间(0: 不提醒, 1: 距离活动时间前提醒)
     */
    private Integer notificationTime;

    /**
     * 通知模版	
     */
    private String notificationTemplate;


}
