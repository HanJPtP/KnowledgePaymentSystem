package com.woniu.users.service;

import com.woniu.users.outlet.dao.mysql.po.UserLevel;
import com.woniu.users.util.PageInfo;

public interface IUserLevelService {

    PageInfo<UserLevel> listUserLevel(Long pageNo,Long pageSize,String levelName);
}
