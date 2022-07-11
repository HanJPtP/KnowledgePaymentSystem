/**
 * FileName: MessageClient
 * Date:     2022/6/21 13:54
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.outlet.client;

import com.woniu.message.inlet.web.controller.IMessageController;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/21 13:54
 * @since 1.0.0
 */
@FeignClient("service-message")
public interface MessageClient extends IMessageController {

}