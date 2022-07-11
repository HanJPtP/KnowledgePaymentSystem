/**
 * FileName: MessageController
 * Date:     2022/6/9 19:14
 * Author: YuanXQ
 * Description:
 */
package com.woniu.message.inlet.web.controller;

import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/9 19:14
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseResult<Void> saveMessage(@RequestBody MessageDTO messageDTO){
        if (messageService.saveMessage(messageDTO)) {
            return new ResponseResult<Void>(200,"消息创建成功, 正在发送", null);
        } else {
            return new ResponseResult<Void>(5003,"消息创建失败, 请重试", null);
        }
    }

    @PutMapping("/message")
    public ResponseResult<Void> receiveMessage(@RequestParam("messageId") Long messageId){
         if(messageService.setMessageReceived(messageId)){
             return new ResponseResult<Void>(200,"消息接受确认成功", null);
         } else {
             return new ResponseResult<Void>(5003,"消息接受确认失败", null);
         }
    }
}