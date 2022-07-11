package com.woniu.users.service;

import com.woniu.api.outlet.web.entity.PointsInfoToUserCenter;
import com.woniu.users.outlet.dao.mysql.po.PointRule;
import com.woniu.users.util.PageInfo;

public interface IPointRuleService {

    /**
     *  多条件查询
     * @param pageNo
     * @param pageSize
     * @param isDiscount 是否有折扣
     * @param status 是否使用
     * @return
     */
    PageInfo<PointRule> listPointRule(Long pageNo, Long pageSize, Integer isDiscount,
                           Integer status);

    /**
     *  新增
     * @param pointRule
     * @return
     */
    int addPointRule(PointRule pointRule);

    /**
     *  修改
     * @param pointRule
     * @return
     */
    int updatePointRule(PointRule pointRule);

    /**
     *  根据id查询
     * @param id
     * @return
     */
    PointRule getPointRuleById(Long id);

    /**
     *  接收订单消息转换后的对象
     * @return
     */
    void getOrderMsg(PointsInfoToUserCenter pointsInfoToUserCenter);
}
