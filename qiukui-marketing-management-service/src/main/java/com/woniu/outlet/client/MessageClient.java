package com.woniu.outlet.client;

import com.woniu.message.inlet.web.controller.IMessageController;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qk
 * @since 2022-06-20-19:47
 */
@FeignClient("service-message")
public interface MessageClient extends IMessageController {
}
