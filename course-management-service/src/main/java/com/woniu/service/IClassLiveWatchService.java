package com.woniu.service;

import com.woniu.intnet.web.vo.WatchUserTimeByLiveVo;

import java.util.List;

public interface IClassLiveWatchService {

    List<WatchUserTimeByLiveVo> listClassLiveWatchByUserid(Long userid)throws Exception;

    Long insertClassLiveWatchWhileIn(Long userid,Long crlid)throws Exception;

    int updateClassLiveWatchById(Long id)throws Exception;

    Long getTotleByCrlid(Long crlid)throws Exception;
}
