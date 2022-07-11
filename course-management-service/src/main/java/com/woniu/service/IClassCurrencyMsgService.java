package com.woniu.service;


import com.woniu.intnet.web.fo.ClassCurrencyMsgAddFo;
import com.woniu.intnet.web.fo.ClassCurrencyMsgUpdateFo;
import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;
import com.woniu.outnet.dao.pojo.PageInfo;

public interface IClassCurrencyMsgService {

    PageInfo<ClassCurrencyMsg> listClassCurrencyMsgByChooseAndPage(Integer pageNo,
                                                                   Integer pageSize,
                                                                   String name,
                                                                   Double startPrice,
                                                                   Double endPrice,
                                                                   String starttime,
                                                                   String endtime)throws Exception;

    ClassCurrencyMsg listClassCurrencyMsgById(Long id)throws Exception;


    //修改状态的功能
    int updateClassCurrencyMsgStatus(Long id,String status)throws Exception;

    //新增数据
    int insertClassCurrencyMsg(ClassCurrencyMsgAddFo classCurrencyMsgAddFo)throws Exception;

    //修改数据,能改为空
    int updateClassCurrencyMsgAllCanNull(ClassCurrencyMsgUpdateFo classCurrencyMsgUpdateFo)throws Exception;
    //修改定时上架时间
    int updateClassCurrencyMsgStartTimeById(Long id,String startTime)throws Exception;
    //修改定时下架时间
    int updateClassCurrencyMsgEndTimeById(Long id,String endTime)throws Exception;

    void timedUpperAndLowerShelves()throws Exception;
}
