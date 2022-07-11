package com.woniu.api.outlet.web.client;

import com.woniu.api.util.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface UserInfoClient {
    @GetMapping("userinfo/getemail")
    ResponseResult<Object> getEmailByUid(@RequestParam("uids") List<Long> uids);
}
