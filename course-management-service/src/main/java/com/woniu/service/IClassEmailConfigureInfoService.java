package com.woniu.service;


import com.woniu.intnet.web.vo.EmailConfigInfoVo;
import com.woniu.outnet.dao.pojo.ClassEmailConfigureInfo;

public interface IClassEmailConfigureInfoService {

    ClassEmailConfigureInfo getClassEmailConfigureInfoOnlyOne()throws Exception;

    int updateClassEmailConfigureInfoById(EmailConfigInfoVo emailConfigInfoVo)throws Exception;
}
