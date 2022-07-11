package com.woniu.inlet.timer.schedule;

import com.woniu.inlet.web.client.RabbitMessageClient;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.message.entity.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ScheduleTask {

    @Resource
    private RabbitMessageClient rabbitMessageClient;

    @Scheduled(cron = "0 0 2 * * ?")
    public void sendMessageToRabbitmq() {
        MessageDTO messageDTO = new MessageDTO("goods.autoshelves.firstexchange", "goodsfirstkey", "111", "418161715@qq.com");
        ResponseResult<Void> result = rabbitMessageClient.saveMessage(messageDTO);
        return;
    }

}
