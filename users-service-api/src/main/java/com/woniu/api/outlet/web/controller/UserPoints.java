package com.woniu.api.outlet.web.controller;

import com.woniu.api.outlet.web.entity.PointsInfoToUserCenter;
import com.woniu.api.outlet.web.entity.PointsQueryResult;
import com.woniu.api.util.ResponseResult;

public interface UserPoints {
    /**
     * 查询积分使用结果
     * @param pointsInfoToUserCenter 积分使用条件
     * @return 积分使用结果
     */
    ResponseResult<PointsQueryResult> fixPointsDiscount(PointsInfoToUserCenter pointsInfoToUserCenter);
}
