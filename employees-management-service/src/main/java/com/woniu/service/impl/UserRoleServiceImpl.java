package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.mapper.UserRoleMapper;
import com.woniu.outlet.dao.mysql.po.UserRole;
import com.woniu.service.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleServiceImpl implements IUserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public int saveUserRoleItem(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }
}
