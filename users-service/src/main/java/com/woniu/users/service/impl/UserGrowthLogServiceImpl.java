package com.woniu.users.service.impl;

import com.woniu.users.outlet.dao.mysql.mapper.UserGrowthLogMapper;
import com.woniu.users.outlet.dao.mysql.po.UserGrowthLog;
import com.woniu.users.service.IUserGrowthLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserGrowthLogServiceImpl implements IUserGrowthLogService {

    private final UserGrowthLogMapper userGrowthLogMapper;

    /**
     *  根据用户uid查询UserGrowthLog集合
     * @param uid
     * @return
     */
    @Override
    public List<UserGrowthLog> getByUid(Long uid) {
        return userGrowthLogMapper.getByUid(uid);
    }
}
