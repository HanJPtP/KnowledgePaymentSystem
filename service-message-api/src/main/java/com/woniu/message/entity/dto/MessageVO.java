/**
 * FileName: MessageVO
 * Date:     2022/6/20 19:58
 * Author: YuanXQ
 * Description:
 */
package com.woniu.message.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/20 19:58
 * @since 1.0.0
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageVO implements Serializable {

    /**
     * 消息id
     */
    private Long id;

    /**
     * 消息内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}