package com.woniu.inlet.web.client;

import com.woniu.inlet.web.fo.UserLoginStatusFo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import org.springframework.web.bind.annotation.*;

@RestController
public interface UserLoginToUsersClient {

    @GetMapping("/userlogin/{id}/get")
    ResponseResult<UserLoginToUserDto> getUserInfoItemById(@PathVariable("id") Long id);

    @PostMapping("/userlogin/update")
    ResponseResult updateUserLoginInfoItem(@RequestBody UserLoginStatusFo userLoginStatusFo);

}
