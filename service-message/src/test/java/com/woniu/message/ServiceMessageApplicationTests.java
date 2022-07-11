package com.woniu.message;

import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.message.outlet.dao.mysql.po.Message;
import com.woniu.message.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ServiceMessageApplicationTests {

    @Autowired
    private MessageService messageService;

    @Test
    void demo() {
        MessageDTO messageDTO = MessageDTO.builder().exchange("test.direct").routingKey("test").content("test").contactMail("test@163.com").build();
        boolean b = messageService.saveMessage(messageDTO);
    }

    @Test
    void demo2() {
        List<Message> b = messageService.listMessagesNotSend();
        b.forEach(System.out::println);
    }

    @Test
    void demo3() {
        messageService.setMessageReceived(1003L);
    }

}
