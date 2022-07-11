package com.woniu.intnet.listenner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.intnet.web.client.MessageClient;
import com.woniu.message.entity.dto.MessageVO;
import com.woniu.outnet.dao.pojo.ClassMessageDuplication;
import com.woniu.service.impl.ClassAppointmentServiceImpl;
import com.woniu.service.impl.ClassMessageDuplicationServiceImpl;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Transactional
public class UserStatusListenner {

    @Resource
    private ClassAppointmentServiceImpl classAppointmentService;
    @Resource
    private MessageClient messageClient;
    @Resource
    private ClassMessageDuplicationServiceImpl classMessageDuplicationService;

    @SneakyThrows
    @RabbitListener(queues = "user-status")
    public void changeUserStatus(String  msg){
        ObjectMapper objectMapper = new ObjectMapper();
        MessageVO messageVO = objectMapper.readValue(msg, MessageVO.class);
        Long id = messageVO.getId();
        classMessageDuplicationService.insertId(new ClassMessageDuplication(id));
        String[] split = messageVO.getContent().split(":");
        Long userid = Long.parseLong(split[0]);
        if(split[1].equals("0")){
            classAppointmentService.updateClassAppointmentUserStatusByUserid(userid, "y");
        }else{
            classAppointmentService.updateClassAppointmentUserStatusByUserid(userid, "n");
        }
        messageClient.receiveMessage(messageVO.getId());
    }
}
