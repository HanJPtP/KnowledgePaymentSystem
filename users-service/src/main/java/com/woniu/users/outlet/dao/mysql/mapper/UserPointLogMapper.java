package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserPointLog;

import java.util.List;

/**
* @author Han
* @description 针对表【user_point_log】的数据库操作Mapper
* @createDate 2022-06-14 19:25:44
* @Entity com.woniu.users.outlet.dao.mysql.po.UserPointLog
*/
public interface UserPointLogMapper extends BaseMapper<UserPointLog> {

    /**
     *  新增用户积分日志对象
     * @param userPointLog
     * @return
     */
    default int addUserPointLog(UserPointLog userPointLog){
        return this.insert(userPointLog);
    }

    /**
     *  根据用户uid查询UserPointLog集合
     * @param uid
     * @return
     */
    default List<UserPointLog> getByUid(Long uid){
        QueryWrapper<UserPointLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.selectList(queryWrapper);
    }
}




