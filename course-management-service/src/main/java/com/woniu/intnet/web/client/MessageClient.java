package com.woniu.intnet.web.client;


import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.message.inlet.web.controller.IMessageController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-message")
public interface MessageClient extends IMessageController {
    /**
     * <p>
     * 添加消息到待发送队列
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/10 16:40
     * @param messageDTO 自定义消息模型
     * @return 200 添加成功, 5003 添加失败
     */
    @PostMapping("/message")
    ResponseResult<Void> saveMessage(@RequestBody MessageDTO messageDTO);

    /**
     * <p>
     * 消息接收确认
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/16 18:20
     * @param messageId
     * @return 200 接收确认成功, 5003 接收确认失败
     */
    @PutMapping("/message")
    ResponseResult<Void> receiveMessage(@RequestParam("messageId") Long messageId);
}
