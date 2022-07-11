package com.woniu.service.impl;

import com.woniu.outnet.dao.mysql.TimeToSendEmailMapper;
import com.woniu.outnet.dao.pojo.TimeToSendEmail;
import com.woniu.service.ITimeToSendEmailService;
import com.woniu.util.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeToSendEmailServiceImpl implements ITimeToSendEmailService {


    private final TimeToSendEmailMapper timeToSendEmailMapper;
    @Override
    public List<TimeToSendEmail> listTimeToSendEmailByQueryWapper(String sendOrnot, Date nowTime) {
        String s = DateFormatUtils.DateToString(nowTime, "yyyy-MM-dd HH:mm:ss");
        String s1 = DateFormatUtils.DateToString(new Date(nowTime.getTime()+600000l), "yyyy-MM-dd HH:mm:ss");
        return timeToSendEmailMapper.listTimeToSendEmailByQueryWapper(sendOrnot,s,s1);
    }
}
