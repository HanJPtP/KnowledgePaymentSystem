package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.mapper.RoleMapper;
import com.woniu.outlet.dao.mysql.po.Role;
import com.woniu.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleMapper roleMapper;

    @Override
    public List<Role> listAllRoleInfo() {
        return roleMapper.selectList(null);
    }
}
