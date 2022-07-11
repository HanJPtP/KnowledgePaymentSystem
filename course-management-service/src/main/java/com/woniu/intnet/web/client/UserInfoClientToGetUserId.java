package com.woniu.intnet.web.client;


import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="users-service")
public interface UserInfoClientToGetUserId{

    @GetMapping(value = "userinfo/getemail")
    ResponseResult<Object> getEmailByUid(@RequestParam("uids") List<Long> uids);
}
