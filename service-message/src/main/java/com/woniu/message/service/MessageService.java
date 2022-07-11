/**
 * FileName: MessageService
 * Date:     2022/6/10 9:41
 * Author: YuanXQ
 * Description:
 */
package com.woniu.message.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.message.outlet.dao.mysql.mapper.MessageMapper;
import com.woniu.message.outlet.dao.mysql.po.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/10 9:41
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper messageMapper;

    private final SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(0L,11L);

    /**
     * <p>
     * 添加消息到代发消息库
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/10 16:46
     * @return boolean
     */
    public boolean saveMessage(MessageDTO messageDTO){
        Message message = Message.builder()
                .id(snowFlakeGenerator.nextId())
                .exchange(messageDTO.getExchange())
                .routingKey(messageDTO.getRoutingKey())
                .content(messageDTO.getContent())
                .contactMail(messageDTO.getContactMail())
                .status(1)
                .createTime(LocalDateTime.now())
                .build();
        return messageMapper.insert(message) > 0;
    }

    /**
     * <p>
     * 查询所有待发送消息
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/10 16:46
     */
    public List<Message> listMessagesNotSend(){
        return messageMapper.selectList(Wrappers.<Message>lambdaQuery()
                .eq(Message::getStatus, 1)
                .ge(Message::getRetryCount, 0));
    }

    /**
     * <p>
     * 将消息设置为已成功发送
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/10 16:46
     * @param messageId
     * @return boolean
     */
    public boolean setMessageSuccess(Long messageId){
        return messageMapper.updateById(
                Message.builder()
                        .id(messageId)
                        .status(2)
                        .build()
        ) > 0;
    }

    /**
     * <p>
     * 将消息设置为已接受
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/10 16:46
     * @param messageId
     * @return boolean
     */
    public boolean setMessageReceived(Long messageId){
        return messageMapper.updateById(
                Message.builder()
                        .id(messageId)
                        .status(3)
                        .build()
        ) > 0;
    }

    /**
     * <p>
     * 消息发送失败减少重试次数
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/10 16:47
     * @param messageId
     * @return boolean
     */
    public boolean setRetryCountMinus(Long messageId){
        Message message = messageMapper.selectById(messageId);
        // 重试次数 > 0, 重试次数 - 1, 否则修改发送状态为失败
        if (message.getRetryCount() > 0) {
            message.setRetryCount(message.getRetryCount() - 1);
        } else {
            message.setStatus(0);
        }
        return messageMapper.updateById(message) > 0;
    }
}