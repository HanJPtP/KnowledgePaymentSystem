package com.woniu.service;

import com.woniu.inlet.web.fo.UserLoginStatusFo;
import com.woniu.inlet.web.vo.UserLoginShowUpdateVo;
import com.woniu.outlet.dao.dto.UserLoginItemToViewDto;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import com.woniu.outlet.dao.dto.UserOneItemToUpdateDto;
import com.woniu.outlet.dao.mysql.po.Employeeinfo;
import com.woniu.outlet.dao.mysql.po.UserLogin;
import com.woniu.outlet.dao.mysql.po.UserRole;

import java.util.List;

public interface IUserLoginService {

    // 通过 userlogin ID 获得注册时间以及状态
    UserLoginToUserDto getUserLoginToUserDtoById(Long id);

    // 通过 ID 更改 userlogin 的状态
    int updateUserLoginInfoItem(UserLoginStatusFo userLoginStatusFo);

    // 查询所有的 UserLoginItemToViewDto
    List<UserLoginItemToViewDto> listAllEmployeesToViewDtoItem();

    // 添加一条 userlogin 数据
    int saveUserLoginItem(UserLogin userLogin);

    // 通过 userlogin ID 查询所有相关信息, 显示到修改界面
    UserLoginShowUpdateVo getUserLoginShowUpdateById(Long id);

    // 查询当前账号是否已存在
    int getUserLoginItemByAccount(String account);

    // 更改 userlogin employeeinfo userrole 信息
    int updateUserLoginAndEmployeeRoleItem(UserLogin userLogin, Employeeinfo employeeinfo, UserRole userRole);

    // 更改登录状态为 2,
    // 更改员工信息为已删除
    int removeUserLoginStatus(UserLogin userLogin);

    // 通过 userlogin 的 account 查询相关信息, 显示到页面进行更改
    UserOneItemToUpdateDto getUserOneItemShowUpdateByAccount(String account);

    // 更新 userlogin employeeinfo 信息
    int updateEmployeeInfoOne(UserLogin userLogin, Employeeinfo employeeinfo);

}
