package com.woniu.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
* @author ThinkPad
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2022-06-19 23:43:36
* @Entity com.woniu.outlet.dao.mysql.po.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Delete("delete from user_role where uid = #{uid}")
    int removeItemByUserId(@Param("uid") Long uid);

}




