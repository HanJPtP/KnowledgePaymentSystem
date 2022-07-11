package com.woniu.service;

import com.woniu.intnet.web.vo.ShowPeopleViewVo;
import com.woniu.outnet.dao.pojo.ClassLiveWatchTotle;

public interface IClassLiveWatchTotleService {

        int updateClassLiveWatchTotleByCrlid(ClassLiveWatchTotle classLiveWatchTotle)throws Exception;

        ShowPeopleViewVo getShowPeopleViewByCrlid(Long crlid)throws Exception;


}
