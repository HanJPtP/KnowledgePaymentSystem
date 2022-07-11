package com.woniu.service.impl;

import com.woniu.inlet.web.fo.UserLoginStatusFo;
import com.woniu.inlet.web.vo.UserLoginShowUpdateVo;
import com.woniu.outlet.dao.dto.UserLoginItemToViewDto;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import com.woniu.outlet.dao.dto.UserOneItemToUpdateDto;
import com.woniu.outlet.dao.mysql.mapper.EmployeeinfoMapper;
import com.woniu.outlet.dao.mysql.mapper.UserLoginMapper;
import com.woniu.outlet.dao.mysql.mapper.UserRoleMapper;
import com.woniu.outlet.dao.mysql.po.Employeeinfo;
import com.woniu.outlet.dao.mysql.po.UserLogin;
import com.woniu.outlet.dao.mysql.po.UserRole;
import com.woniu.service.IUserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLoginServiceImpl implements IUserLoginService {

    private final UserRoleMapper userRoleMapper;

    private final EmployeeinfoMapper employeeinfoMapper;

    private final UserLoginMapper userLoginMapper;

    /**
     * 通过 userlogin ID 获得注册时间以及状态
     * @param id
     * @return
     */
    @Override
    public UserLoginToUserDto getUserLoginToUserDtoById(Long id) {
        return userLoginMapper.getUserLoginToUserDtoById(id);
    }

    /**
     * 通过 ID 更改 userlogin 的状态
     * @param userLoginStatusFo
     * @return
     */
    @Override
    public int updateUserLoginInfoItem(UserLoginStatusFo userLoginStatusFo) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUid(userLoginStatusFo.getId());
        userLogin.setStatus(userLoginStatusFo.getStatus());
        userLogin.setModifiedTime(new Date());
        return userLoginMapper.updateById(userLogin);
    }

    /**
     * 查询所有的 userlogin
     * @return
     */
    @Override
    public List<UserLoginItemToViewDto> listAllEmployeesToViewDtoItem() {
        return userLoginMapper.listAllEmployeesToViewDtoItem();
    }

    /**
     * 添加一条 userlogin 数据
     * @param userLogin
     * @return
     */
    @Override
    public int saveUserLoginItem(UserLogin userLogin) {
        return userLoginMapper.insert(userLogin);
    }

    /**
     * 通过 userlogin ID 获得需要显示到页面的更新信息
     * @param id
     * @return
     */
    @Override
    public UserLoginShowUpdateVo getUserLoginShowUpdateById(Long id) {
        return userLoginMapper.getUserLoginShowUpdateById(id);
    }

    /**
     * 查询当前账号是否已存在
     * @param account
     * @return
     */
    @Override
    public int getUserLoginItemByAccount(String account) {
        return userLoginMapper.getUserLoginItemByAccount(account);
    }

    /**
     * 更新 userLogin userRole employeeinfo
     * @param userLogin
     * @param employeeinfo
     * @param userRole
     * @return
     */
    @Override
    public int updateUserLoginAndEmployeeRoleItem(UserLogin userLogin, Employeeinfo employeeinfo, UserRole userRole) {
        userRoleMapper.removeItemByUserId(userLogin.getUid());
        userRoleMapper.insert(userRole);
        employeeinfoMapper.updateById(employeeinfo);
        return userLoginMapper.updateById(userLogin);
    }

    /**
     * 更改登录状态为 2,
     * 更改员工信息为已删除
     * @param userLogin
     * @return
     */
    @Override
    public int removeUserLoginStatus(UserLogin userLogin) {
        int i = employeeinfoMapper.removeEmployeeInfo(userLogin.getUid());
        if (i > 0)
            userLoginMapper.updateById(userLogin);
        return i;
    }

    /**
     * 通过 userlogin 的 account 查询相关信息, 显示到页面进行更改
     * @param account
     * @return
     */
    @Override
    public UserOneItemToUpdateDto getUserOneItemShowUpdateByAccount(String account) {
        return userLoginMapper.getUserOneItemShowUpdateByAccount(account);
    }

    /**
     * 更新 userlogin employeeinfo 信息
     * @param userLogin
     * @param employeeinfo
     * @return
     */
    @Override
    public int updateEmployeeInfoOne(UserLogin userLogin, Employeeinfo employeeinfo) {
        employeeinfoMapper.updateById(employeeinfo);
        return userLoginMapper.updateById(userLogin);
    }
}
