package com.woniu.users.inlet.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  免费会员等级信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLevelDto implements Serializable {

    // 会员等级id
    private Long levelId;

    // 等级级别数
    private String levelNumber;

    // 等级名称
    private String levelName;

    // 背景颜色
    private String bgcolor;

    // 最低要求分数
    private Integer minPoint;

    // 最大要求分数
    private Integer maxPoint;

    // 拥有权益id
    private String rightIds;

    // 是否是会员
    private Integer ismember;

    // 花费金额
    private Double money;

    // 成长值
    private Integer growthNum;

    // 拥有优惠券id
    private Long couponId;

    // 送的积分数
    private Integer points;

}
