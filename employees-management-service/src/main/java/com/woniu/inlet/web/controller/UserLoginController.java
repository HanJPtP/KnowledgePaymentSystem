package com.woniu.inlet.web.controller;

import com.alibaba.nacos.common.utils.StringUtils;
import com.woniu.inlet.web.fo.EmployeeInfoUpdateOneFo;
import com.woniu.inlet.web.fo.EmployeeInfoUpdateStatusFo;
import com.woniu.inlet.web.fo.UserLoginAddFo;
import com.woniu.inlet.web.fo.UserLoginUpdateFo;
import com.woniu.inlet.web.timer.RedisRemoveIdempotent;
import com.woniu.inlet.web.vo.UserLoginShowUpdateVo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outlet.dao.dto.UserLoginItemToViewDto;
import com.woniu.outlet.dao.dto.UserOneItemToUpdateDto;
import com.woniu.outlet.dao.mysql.po.Employeeinfo;
import com.woniu.outlet.dao.mysql.po.Role;
import com.woniu.outlet.dao.mysql.po.UserLogin;
import com.woniu.outlet.dao.mysql.po.UserRole;
import com.woniu.service.impl.UserLoginServiceImpl;
import com.woniu.service.impl.UserRoleServiceImpl;
import com.woniu.service.impl.EmployeesInfoServcieImpl;
import com.woniu.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class UserLoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ScheduledThreadPoolExecutor executor;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Resource
    private UserLoginServiceImpl userLoginService;

    @Autowired
    private SnowFlakeGenerator snowFlakeGenerator;

    @Autowired
    private EmployeesInfoServcieImpl employeesInfoServcie;

    /**
     * 查询需要显示到页面所有 employeeinfo 信息列表
     * @return
     */
    @GetMapping("/employees/list")
    public ResponseResult listAllEmployeesToViewDtoItem() {
        ResponseResult responseResult;
        List<UserLoginItemToViewDto> loginItemToViewDtos = userLoginService.listAllEmployeesToViewDtoItem();
        if (loginItemToViewDtos.size() > 0)
            responseResult = new ResponseResult(200, "ok", loginItemToViewDtos);
        else
            responseResult = new ResponseResult(500, "未查询到相关信息", null);
        return responseResult;
    }

    /**
     * 进入添加页面需要获取的信息
     * @return
     */
    @GetMapping("/employee/showadd")
    public ResponseResult showAddEmplyeeRoleList() {
        ResponseResult responseResult;
        List<Role> roles = roleService.listAllRoleInfo();
        if (roles.size() > 0) {
            // 填完线程池延时任务, 一小时后自动删除该 token
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForSet().add("employee-showadd-token", token);
            RedisRemoveIdempotent idempotent = new RedisRemoveIdempotent("employee-showadd-token", token, redisTemplate);
            executor.schedule(idempotent, 60, TimeUnit.MINUTES);
            Map map = new HashMap();
            map.put("token", token);
            map.put("roles", roles);
            responseResult = new ResponseResult(200, "ok", map);
        }
        else
            responseResult = new ResponseResult(500, "未查询到相关信息", null);
        return responseResult;
    }

    /**
     * 添加一条 userlogin employee userrole 信息
     * @param userLoginAddFo
     * @param username
     * @return
     */
    @Transactional
    @PostMapping("/employee/add")
    public ResponseResult saveEmployeesItem(@RequestBody UserLoginAddFo userLoginAddFo,
                                            @RequestHeader("x-username") String username) {
        ResponseResult responseResult;
        if (userLoginService.getUserLoginItemByAccount(userLoginAddFo.getAccount()) <= 0) {
            // 先从数据库判断当前账号是否已被注册
            if (redisTemplate.opsForSet().remove("employee-showadd-token", userLoginAddFo.getToken()) <= 0) {
                responseResult = new ResponseResult(400, "请勿重复添加", null);
            } else {
                // 添加一条 userlogin 数据
                // 添加一条 user_role 数据
                // 添加一条 employeeinfo 数据
                long uid = snowFlakeGenerator.nextId();
                long employeeinfoId = snowFlakeGenerator.nextId();
                String password = passwordEncoder.encode(userLoginAddFo.getPassword());
                UserLogin userLogin = new UserLogin(uid, userLoginAddFo.getAccount(), password, userLoginAddFo.getStatus(), null, new Date());
                UserRole userRole = new UserRole(uid, userLoginAddFo.getRole());
                Employeeinfo employeeinfo = new Employeeinfo(employeeinfoId, userLoginAddFo.getUsername(), uid, userLoginAddFo.getTel(), userLoginAddFo.getImg(), userLoginAddFo.getVx(), userLoginAddFo.getQq(), userLoginAddFo.getEmail(), userLoginAddFo.getProfile(), userLoginAddFo.getStatus(), new Date(), userLoginAddFo.getSex(), userLoginAddFo.getBirthday(), username, "n");
                userRoleService.saveUserRoleItem(userRole);
                employeesInfoServcie.saveEmployeeInfoItem(employeeinfo);
                int i = userLoginService.saveUserLoginItem(userLogin);
                if (i > 0)
                    responseResult = new ResponseResult(200, "ok", null);
                else
                    responseResult = new ResponseResult(500, "添加失败", null);
            }
        } else
            responseResult = new ResponseResult(500, "该账号已存在, 请重新输入", null);
        return responseResult;
    }

    /**
     * 进入修改页面需要获取的信息
     * @param id
     * @return
     */
    @GetMapping("/employee/{id}/showupdate")
    public ResponseResult showUpdateEmployeeInfoItem(@PathVariable("id") Long id) {
        ResponseResult responseResult;
        List<Role> roles = roleService.listAllRoleInfo();
        UserLoginShowUpdateVo showUpdateById = userLoginService.getUserLoginShowUpdateById(id);
        if (showUpdateById != null) {
            // 填完线程池延时任务, 一小时后自动删除该 token
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForSet().add("employee-showupdate-token", token);
            RedisRemoveIdempotent idempotent = new RedisRemoveIdempotent("employee-showupdate-token", token, redisTemplate);
            executor.schedule(idempotent, 60, TimeUnit.MINUTES);
            Map map = new HashMap();
            map.put("roles", roles);
            map.put("showUpdateById", showUpdateById);
            map.put("employee-showupdate-token", token);
            responseResult = new ResponseResult(200, "ok", map);
        } else
            responseResult = new ResponseResult(500, "未查询到相关信息", null);
        return responseResult;
    }

    /**
     * 修改 userlogin employeeinfo userrole 信息
     * @param userLoginUpdateFo
     * @return
     */
    @PostMapping("/employee/update")
    public ResponseResult updateEmployeeInfoItem(@RequestBody UserLoginUpdateFo userLoginUpdateFo) {
        ResponseResult responseResult;
        if (StringUtils.equals(userLoginUpdateFo.getAccount(), userLoginUpdateFo.getOldaccount()) || userLoginService.getUserLoginItemByAccount(userLoginUpdateFo.getAccount()) <= 0) {
            if (redisTemplate.opsForSet().remove("employee-showupdate-token", userLoginUpdateFo.getToken()) <= 0) {
//            if (redisTemplate.opsForSet().remove("employee-showupdate-token", userLoginUpdateFo.getToken()) <= 0) {
                responseResult = new ResponseResult(400, "请勿重复提交", null);
            } else {
                // 修改userlogin
                // 修改 employeeinfo
                // 删除 user_role
                // 添加 user_role
                String password = null;
                if (StringUtils.equals(userLoginUpdateFo.getOldpassword(), userLoginUpdateFo.getNewpassword())) {

                } else {
                    password = passwordEncoder.encode(userLoginUpdateFo.getNewpassword());
                }
                UserLogin userLogin = new UserLogin(userLoginUpdateFo.getUid(), userLoginUpdateFo.getAccount(), password, null, new Date(), null);
                Employeeinfo employeeinfo = new Employeeinfo(userLoginUpdateFo.getEid(), userLoginUpdateFo.getUsername(), userLoginUpdateFo.getUid(), userLoginUpdateFo.getTel(), userLoginUpdateFo.getImg(), userLoginUpdateFo.getVx(), userLoginUpdateFo.getQq(), userLoginUpdateFo.getEmail(), userLoginUpdateFo.getProfile(), null, null, userLoginUpdateFo.getSex(), userLoginUpdateFo.getBirthday(), null, null);
                UserRole userRole = new UserRole(userLogin.getUid(), userLoginUpdateFo.getRole());
                int i = userLoginService.updateUserLoginAndEmployeeRoleItem(userLogin, employeeinfo, userRole);
                if (i > 0)
                    responseResult = new ResponseResult(200, "ok", null);
                else
                    responseResult = new ResponseResult(500, "添加失败", null);
            }
        }
        else
            responseResult = new ResponseResult(400, "当前账号已存在, 请重新输入", null);
        return responseResult;
    }

    /**
     * 更改员工信息为删除状态, 同时更改登录状态为 2
     * @param id
     * @return
     */
    @GetMapping("/emplyee/{id}/delete")
    public ResponseResult removeUserLogin(@PathVariable("id") Long id) {
        // 更改登录状态为 2,
        // 更改员工信息为已删除
        ResponseResult responseResult;
        UserLogin userLogin = new UserLogin();
        userLogin.setUid(id);
        userLogin.setStatus("2");
        int i = userLoginService.removeUserLoginStatus(userLogin);
        if (i > 0)
            responseResult = new ResponseResult(200, "ok", null);
        else
            responseResult = new ResponseResult(500, "删除失败", null);
        return responseResult;
    }

    /**
     * 修改 employeeinfo 状态
     * @param employeeInfoUpdateStatusFo
     * @return
     */
    @PostMapping("/employees/status")
    public ResponseResult updateEmployeeInfoStatus(@RequestBody EmployeeInfoUpdateStatusFo employeeInfoUpdateStatusFo) {
        ResponseResult responseResult;
        if (!employeeInfoUpdateStatusFo.getStatus().equals("0") && !employeeInfoUpdateStatusFo.getStatus().equals("1")){
            return new ResponseResult(400, "参数错误...", null);
        }
        Employeeinfo employeeinfo = new Employeeinfo();
        employeeinfo.setId(employeeInfoUpdateStatusFo.getEid());
        employeeinfo.setStatus(employeeInfoUpdateStatusFo.getStatus());
        int i = employeesInfoServcie.updateEmployeeInfoStatus(employeeinfo);
        if (i > 0)
            responseResult = new ResponseResult(200, "ok", null);
        else
            responseResult = new ResponseResult(500, "更改状态失败", null);
        return responseResult;
    }

    /**
     * 通过 userlogin 的 account 查询相关信息, 显示到页面进行更改
     * @param account
     * @return
     */
    @GetMapping("/employee/{account}/showupdateone")
    public ResponseResult showUpdateTheEmployeeInfo(@PathVariable("account") String account) {
        ResponseResult responseResult;
        UserOneItemToUpdateDto userOneItemToUpdateDto = userLoginService.getUserOneItemShowUpdateByAccount(account);
        if (userOneItemToUpdateDto != null) {
            // 填完线程池延时任务, 一小时后自动删除该 token
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForSet().add("employee-showupdateone-token", token);
            RedisRemoveIdempotent idempotent = new RedisRemoveIdempotent("employee-showupdateone-token", token, redisTemplate);
            executor.schedule(idempotent, 60, TimeUnit.MINUTES);
            Map map = new HashMap();
            map.put("employee-showupdateone-token", token);
            map.put("userOneItemToUpdateDto", userOneItemToUpdateDto);
            responseResult = new ResponseResult(200, "ok", map);
        }
        else
            responseResult = new ResponseResult(500, "查询用户信息失败", null);
        return responseResult;
    }

    /**
     * 修改员工自己的个人信息
     * @param fo
     * @return
     */
    @PostMapping("/employee/updateone")
    public ResponseResult updateEmployeeInfoOneItem(@RequestBody EmployeeInfoUpdateOneFo fo) {
        ResponseResult responseResult;
        if (redisTemplate.opsForSet().remove("employee-showupdateone-token", fo.getToken()) > 0) {
            String password = null;
            if (!StringUtils.equals(fo.getOldpassword(), fo.getNewpassword())) {
                password = passwordEncoder.encode(fo.getNewpassword());
            }
            UserLogin userLogin = new UserLogin(fo.getUid(), fo.getAccount(), password, null, new Date(), null);
            Employeeinfo employeeinfo = new Employeeinfo(fo.getEid(), fo.getUsername(), fo.getUid(), fo.getTel(), null, fo.getVx(), fo.getQq(), fo.getEmail(), null, null, null, null, null, null, null);
            int i = userLoginService.updateEmployeeInfoOne(userLogin, employeeinfo);
            if (i > 0)
                responseResult = new ResponseResult(200, "ok", null);
            else
                responseResult = new ResponseResult(500, "更改失败", null);
        } else
            responseResult = new ResponseResult(400, "请勿重复提交", null);
        return responseResult;
    }

}
