package com.woniu.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.inlet.web.vo.UserLoginShowUpdateVo;
import com.woniu.outlet.dao.dto.UserLoginItemToViewDto;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import com.woniu.outlet.dao.dto.UserOneItemToUpdateDto;
import com.woniu.outlet.dao.mysql.po.UserLogin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author ThinkPad
* @description 针对表【user_login】的数据库操作Mapper
* @createDate 2022-06-19 21:50:49
* @Entity com.woniu.outlet.dao.mysql.po.UserLogin
*/
public interface UserLoginMapper extends BaseMapper<UserLogin> {

    // 获得 userlogin 状态信息, users 调用的方法
    UserLoginToUserDto getUserLoginToUserDtoById(@Param("id") Long id);

    // 获得所有 employees 部分信息
    List<UserLoginItemToViewDto> listAllEmployeesToViewDtoItem();

    // 通过 userlogin ID 查询相关需要修改的信息
    UserLoginShowUpdateVo getUserLoginShowUpdateById(@Param("id") Long id);

    // 通过 account 判断当前账号是否已存在
    @Select("select count(*) from user_login where account = #{account}")
    int getUserLoginItemByAccount(@Param("account") String account);

    @Select("select e.id eid, ul.uid, ul.account, ul.password, e.username, e.vx, e.qq, e.tel, e.email " +
            "from user_login ul \n" +
            "\t\tLEFT JOIN employeeinfo e on ul.uid = e.uid \n" +
            "\t\tWHERE ul.account = #{account}")
    UserOneItemToUpdateDto getUserOneItemShowUpdateByAccount(String account);

}




