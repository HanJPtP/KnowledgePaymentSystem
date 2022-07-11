package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserAddr;

/**
* @author Han
* @description 针对表【user_addr】的数据库操作Mapper
* @createDate 2022-06-15 17:57:38
* @Entity com.woniu.users.outlet.dao.mysql.po.UserAddr
*/
public interface UserAddrMapper extends BaseMapper<UserAddr> {

    /**
     *  根据uid查询默认地址信息
     * @param uid
     * @return
     */
    default UserAddr getByUid(Long uid){
        QueryWrapper<UserAddr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("is_default", 1);
        return this.selectOne(queryWrapper);
    }

}




