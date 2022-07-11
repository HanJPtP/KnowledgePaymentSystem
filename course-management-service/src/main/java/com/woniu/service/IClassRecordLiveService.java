package com.woniu.service;


import com.woniu.intnet.web.fo.ClassRecordLiveFo;
import com.woniu.intnet.web.fo.ClassRecordLiveUpdateFo;
import com.woniu.outnet.dao.pojo.ClassRecordLive;
import com.woniu.outnet.dao.pojo.PageInfo;

import java.util.Date;

public interface IClassRecordLiveService {


    PageInfo<ClassRecordLive> listAllClassRecordLiveByChooseAndPage(Integer pageNo, Integer pageSize, String name, String starttime, String endtime)throws Exception;

    int insertClassRecordLive(ClassRecordLiveFo classRecordLiveFo)throws Exception;

    int updateClassRecordLiveWhileBeginShow(Long id,Date startTime)throws Exception;

    int updateClassRecordLiveWhileEndShow(Long id,Date endTime)throws Exception;

    int updateClassRecordLiveStatusById(Long id,String status)throws Exception;

    int updateClassRecordLiveAll(ClassRecordLiveUpdateFo classRecordLiveUpdateFo)throws Exception;
}
