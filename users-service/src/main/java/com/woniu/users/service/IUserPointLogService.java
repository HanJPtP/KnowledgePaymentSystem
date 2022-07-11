package com.woniu.users.service;

import com.woniu.users.outlet.dao.mysql.po.UserPointLog;

import java.util.List;

public interface IUserPointLogService {

    /**
     *  根据用户id查询UserPointLog对象集合
     * @param uid
     * @return
     */
    List<UserPointLog> getPointLogByUid(Long uid);
}
