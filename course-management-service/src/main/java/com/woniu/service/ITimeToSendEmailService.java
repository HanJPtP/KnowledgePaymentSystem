package com.woniu.service;

import com.woniu.outnet.dao.pojo.TimeToSendEmail;

import java.util.Date;
import java.util.List;

public interface ITimeToSendEmailService {


    List<TimeToSendEmail> listTimeToSendEmailByQueryWapper(String sendOrnot,
                                                           Date date);
}
