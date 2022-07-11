package com.woniu.users.service.impl;

import com.woniu.users.outlet.dao.mysql.mapper.UserPointLogMapper;
import com.woniu.users.outlet.dao.mysql.po.UserPointLog;
import com.woniu.users.service.IUserPointLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPointLogServiceImpl implements IUserPointLogService {
    private final UserPointLogMapper userPointLogMapper;

    /**
     *  根据用户id查询UserPointLog对象集合
     * @param uid
     * @return
     */
    @Override
    public List<UserPointLog> getPointLogByUid(Long uid) {
        return userPointLogMapper.getByUid(uid);
    }
}
