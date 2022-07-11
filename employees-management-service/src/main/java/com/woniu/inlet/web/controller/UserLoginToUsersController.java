package com.woniu.inlet.web.controller;

import com.woniu.inlet.web.fo.UserLoginStatusFo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import com.woniu.service.impl.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 为 users 调用的 API
 */

@RestController
public class UserLoginToUsersController {

    @Autowired
    private UserLoginServiceImpl userLoginService;

    /**
     * 通过 userlogin ID 获得 用户状态和注册时间
     * @param id
     * @return
     */
    @GetMapping("/userlogin/{id}/get")
    public ResponseResult<UserLoginToUserDto> getUserInfoItemById(@PathVariable("id") Long id) {
        ResponseResult responseResult;
        UserLoginToUserDto userLoginToUserDto = userLoginService.getUserLoginToUserDtoById(id);
        if (userLoginToUserDto != null)
            responseResult = new ResponseResult(200, "ok", userLoginToUserDto);
        else
            responseResult = new ResponseResult(500, "未查询到相关信息", null);
        return responseResult;
    }

    /**
     * 通过 userlogin ID 更改用户状态
     * @param userLoginStatusFo
     * @return
     */
    @PostMapping("/userlogin/update")
    public ResponseResult updateUserLoginInfoItem(@RequestBody UserLoginStatusFo userLoginStatusFo) {
        ResponseResult responseResult;
        if (!userLoginStatusFo.getStatus().equals("0") && !userLoginStatusFo.getStatus().equals("1")&& !userLoginStatusFo.getStatus().equals("2")){
            return new ResponseResult(400, "参数错误...", null);
        }
        int i = userLoginService.updateUserLoginInfoItem(userLoginStatusFo);
        if (i > 0)
            responseResult = new ResponseResult(200, "ok", null);
        else
            responseResult = new ResponseResult(500, "修改失败", null);
        return responseResult;
    }


}
