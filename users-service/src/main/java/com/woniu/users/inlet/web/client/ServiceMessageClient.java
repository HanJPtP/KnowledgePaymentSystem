package com.woniu.users.inlet.web.client;

import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.message.entity.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("service-message")
public interface ServiceMessageClient {

    // 发送消息
    @PostMapping("/message")
    ResponseResult<Void> saveMessage(@RequestBody MessageDTO messageDTO);

    // 确认接收
    @PutMapping("/message")
    ResponseResult<Void> receiveMessage(@RequestParam("messageId") Long messageId);
}
