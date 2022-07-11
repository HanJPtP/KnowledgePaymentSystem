package com.woniu.outlet.dao.mysql.mapper;

import com.woniu.outlet.dao.mysql.po.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author ThinkPad
* @description 针对表【user_login】的数据库操作Mapper
* @createDate 2022-06-10 16:56:31
* @Entity com.woniu.outlet.dao.mysql.po.UserLogin
*/
@Mapper
public interface UserLoginMapper {

    // 进行登录验证查询
    UserLogin getUsersByUsername(@Param("username") String username);

    // 获得当前 uid 用户的所有权限
    @Select("select p.percode from user_login ul \n" +
            " LEFT JOIN user_role ur on ul.uid = ur.uid \n" +
            "LEFT JOIN role_perms rp on ur.roleid = rp.roleid\n" +
            "LEFT JOIN permission p on rp.permid = p.id where ul.uid = #{uid}")
    List<String> getUsersAllPerms(@Param("uid") Long uid);

}




