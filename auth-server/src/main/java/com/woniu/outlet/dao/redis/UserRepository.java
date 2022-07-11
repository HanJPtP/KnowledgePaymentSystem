package com.woniu.outlet.dao.redis;

import com.woniu.outlet.dao.mysql.mapper.UserLoginMapper;
import com.woniu.outlet.dao.mysql.po.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private UserLoginMapper usersMapper;

    /**
     * 查询rbacuser
     * @param username
     * @return
     */
    public UserLogin getUsers(String username) {
        UserLogin users;
        users = usersMapper.getUsersByUsername(username);
        return users;
    }

}
