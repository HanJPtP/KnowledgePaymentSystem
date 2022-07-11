package com.woniu.users.inlet.web.client;

import com.woniu.inlet.web.fo.UserLoginStatusFo;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import com.woniu.users.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("employees-management-service")
public interface UserStatusClient {

    @GetMapping("/userlogin/{id}/get")
    ResponseResult<UserLoginToUserDto> getUserInfoItemById(@PathVariable("id") Long id);

    @PostMapping("/userlogin/update")
    ResponseResult updateUserLoginInfoItem(@RequestBody UserLoginStatusFo userLoginStatusFo);
}
