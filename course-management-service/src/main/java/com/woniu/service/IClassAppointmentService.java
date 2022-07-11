package com.woniu.service;

import com.woniu.outnet.dao.pojo.ClassAppointment;

import java.util.Date;
import java.util.List;

public interface IClassAppointmentService {


    int deleteClassAppointmentById(Long userid,Long crlid)throws Exception;

    Long insertClassAppointment(Long userid,Long crlid)throws Exception;

    int updateClassAppointmentByIdToSendOrNotIsY(List<Long> ids)throws Exception;

    List<ClassAppointment> listClassAppointmentBySendOrNotAndTime(String sendOrNot, Date nowTime)throws Exception;

    int updateClassAppointmentUserStatusByUserid(Long userid,String userStatus)throws Exception;
}
