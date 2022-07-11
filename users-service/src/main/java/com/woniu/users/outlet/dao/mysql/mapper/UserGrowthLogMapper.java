package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserGrowthLog;
import com.woniu.users.outlet.dao.mysql.po.UserPointLog;

import java.util.List;

/**
* @author Han
* @description 针对表【user_growth_log】的数据库操作Mapper
* @createDate 2022-06-14 19:24:58
* @Entity com.woniu.users.outlet.dao.mysql.po.UserGrowthLog
*/
public interface UserGrowthLogMapper extends BaseMapper<UserGrowthLog> {

    /**
     *  新增用户成长值日志对象
     * @param userGrowthLog
     * @return
     */
    default int addGrowthNumLog(UserGrowthLog userGrowthLog){
        return this.insert(userGrowthLog);
    }


    /**
     *  根据用户uid查询UserGrowthLog集合
     * @param uid
     * @return
     */
    default List<UserGrowthLog> getByUid(Long uid){
        QueryWrapper<UserGrowthLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.selectList(queryWrapper);
    }

}




