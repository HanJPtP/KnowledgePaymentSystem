package com.woniu.users.service;

import com.woniu.users.outlet.dao.mysql.po.UserGrowthLog;

import java.util.List;

public interface IUserGrowthLogService {

    /**
     *  根据用户uid查询UserGrowthLog集合
     * @param uid
     * @return
     */
    List<UserGrowthLog> getByUid(Long uid);
}
