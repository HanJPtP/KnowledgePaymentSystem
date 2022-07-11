package com.woniu.users.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.users.outlet.dao.mysql.mapper.UserLevelMapper;
import com.woniu.users.outlet.dao.mysql.po.UserLevel;
import com.woniu.users.service.IUserLevelService;
import com.woniu.users.util.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLevelServiceImpl implements IUserLevelService {

    private final UserLevelMapper userLevelMapper;

    /**
     *  分页条件查询UserLevel对象
     * @param pageNo
     * @param pageSize
     * @param levelName
     * @return
     */
    @Override
    public PageInfo<UserLevel> listUserLevel(Long pageNo, Long pageSize, String levelName) {
        Page page = new Page(pageNo, pageSize);
        IPage iPage = userLevelMapper.listUserLevel(page, levelName);
        PageInfo<UserLevel> pageInfo = new PageInfo<>(iPage);
        return pageInfo;
    }
}
