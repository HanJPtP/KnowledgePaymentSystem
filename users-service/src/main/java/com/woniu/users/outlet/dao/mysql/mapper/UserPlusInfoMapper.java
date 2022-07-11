package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserPlusInfo;

/**
* @author Han
* @description 针对表【user_plus_info】的数据库操作Mapper
* @createDate 2022-06-11 15:04:29
* @Entity com.woniu.users.outlet.dao.mysql.po.UserPlusInfo
*/
public interface UserPlusInfoMapper extends BaseMapper<UserPlusInfo> {

    /**
     *  根据用户uid查询对象
     * @param uid
     * @return
     */
    default UserPlusInfo getUserPlusInfoByUid(Long uid){
        QueryWrapper<UserPlusInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.selectOne(queryWrapper);
    }

}




