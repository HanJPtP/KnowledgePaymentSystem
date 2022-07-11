package com.woniu.service;

import com.woniu.outlet.dao.mysql.po.Role;

import java.util.List;

public interface IRoleService {

    // 显示所有 role 信息
    List<Role> listAllRoleInfo();

}
