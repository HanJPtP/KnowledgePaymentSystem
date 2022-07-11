/**
 * FileName: MessageDTO
 * Date:     2022/6/9 19:23
 * Author: YuanXQ
 * Description:
 */
package com.woniu.message.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/9 19:23
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由
     */
    private String routingKey;

    /**
     * 消息内容 (对象使用json格式)
     */
    private String content;

    /**
     * 消息发送失败时联系邮箱
     */
    private String contactMail;
}