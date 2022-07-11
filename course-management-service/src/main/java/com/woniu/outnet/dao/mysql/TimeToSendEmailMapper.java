package com.woniu.outnet.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outnet.dao.pojo.TimeToSendEmail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimeToSendEmailMapper extends BaseMapper<TimeToSendEmail> {


    List<TimeToSendEmail> listTimeToSendEmailByQueryWapper(@Param("sendOrNot") String sendOrnot,
                                                           @Param("leftTime") String leftTime,
                                                           @Param("rightTime") String rightTime);
}
