package com.woniu.service.impl;

import com.woniu.intnet.web.client.UserInfoClientToGetUserId;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outnet.dao.mysql.ClassAppointmentMapper;
import com.woniu.outnet.dao.mysql.ClassRecordLiveMapper;
import com.woniu.outnet.dao.pojo.ClassAppointment;
import com.woniu.outnet.dao.pojo.ClassRecordLive;
import com.woniu.service.IClassAppointmentService;
import com.woniu.util.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassAppointmentServiceImpl implements IClassAppointmentService {

    private final ClassAppointmentMapper classAppointmentMapper;

    private final ClassRecordLiveMapper classRecordLiveMapper;

    private final UserInfoClientToGetUserId toGetUserId;

    private final SnowFlakeGenerator snowFlakeGenerator;

    /**
     * 取消预约
     * @param userid
     * @param crlid
     * @return
     * @throws Exception
     */
    @Override
    public int deleteClassAppointmentById(Long userid,Long crlid) throws Exception {
        //先判断用户是否存在
        ResponseResult<Object> emailByUid = toGetUserId.getEmailByUid(Collections.singletonList(userid));
        List<String> data = (List<String>) emailByUid.getData();
        if(data.size()<=0){
            return -3;//表示用户不存在
        }
        //判断课程是否存在
        ClassRecordLive classRecordLive = classRecordLiveMapper.listClassLiveWatchById(crlid);
        if(classRecordLive==null){
            return -1;//表示课程不存在
        }
        //判断用一个人,在同一个预播时间上,是否重复新增同一个数据
        ClassAppointment classAppointmentByUseridAndCrlidAndEstartTime = classAppointmentMapper.getClassAppointmentByUseridAndCrlidAndEstartTime(userid, crlid, classRecordLive.getEstarttime());
        if(classAppointmentByUseridAndCrlidAndEstartTime==null){
            return -2;//表示该时段该人未关注该课不用删除
        }
        return classAppointmentMapper.deleteClassAppointmentById(classAppointmentByUseridAndCrlidAndEstartTime.getId());
    }

    /**
     * 新增预约
     * @param userid
     * @param crlid
     * @return
     * @throws Exception
     */
    @Override
    public Long insertClassAppointment(Long userid, Long crlid) throws Exception {
        //根据id判断账号是否存在
        ResponseResult<Object> emailByUid = toGetUserId.getEmailByUid(Collections.singletonList(userid));
        List<String> data = (List<String>) emailByUid.getData();
        if(data.size()<=0){
            return -3l;//表示用户不存在
        }
        //根据课程判断课程是否存在
        ClassRecordLive classRecordLive = classRecordLiveMapper.listClassLiveWatchById(crlid);
        if(classRecordLive==null){
            return -1l;//表示课程不存在
        }
        //判断用一个人,在同一个预播时间上,是否重复新增同一个数据
        int classAppointmentIsExists = classAppointmentMapper.getClassAppointmentIsExists(userid, crlid, classRecordLive.getEstarttime());
        if(classAppointmentIsExists>=1){
            return -2l;//表示该时段该课已被该用户关注
        }
        //查有无该数据
        ClassAppointment classAppointment = new ClassAppointment(snowFlakeGenerator.nextId(),crlid,userid,new Date(),classRecordLive.getEstarttime(),"n", "y",classRecordLive);
        classAppointmentMapper.insertClassAppointment(classAppointment);
        //再增加
        return classAppointment.getId();
    }

    /**
     * 修改消息为已发送
     * 批量修改
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassAppointmentByIdToSendOrNotIsY(List<Long> ids) throws Exception {
        int i = classAppointmentMapper.updateClassAppointmentByListIdToChangeSendOrNot(ids);
        return i;
    }

    /**
     * 判断时间段有无需要发送的任务
     * @param sendOrNot
     * @param nowTime
     * @return
     * @throws Exception
     */
    @Override
    public List<ClassAppointment> listClassAppointmentBySendOrNotAndTime(String sendOrNot, Date nowTime) throws Exception {
        String s = DateFormatUtils.DateToString(nowTime, "yyyy-MM-dd HH:mm:ss");
        String s1 = DateFormatUtils.DateToString(new Date(nowTime.getTime()+600000l), "yyyy-MM-dd HH:mm:ss");
        return classAppointmentMapper.listClassAppointmentBySendOrNotAndTime(sendOrNot,s,s1);
    }

    /**
     * 更改发送消息的用户的状态的方法
     * @param userid
     * @param userStatus
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassAppointmentUserStatusByUserid(Long userid, String userStatus) throws Exception {
        return classAppointmentMapper.updateClassAppointmentUserStatusByUserid(userid,userStatus);
    }
}
