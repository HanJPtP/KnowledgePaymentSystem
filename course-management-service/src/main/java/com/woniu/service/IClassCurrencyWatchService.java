package com.woniu.service;

import com.woniu.intnet.web.vo.WatchUserTimeByCurrencyVo;

import java.util.List;

public interface IClassCurrencyWatchService {

    List<WatchUserTimeByCurrencyVo> listWatchUserTimeByCurrencyVoByUserid(Long userid);

    Long insertClassCurrencyWatch(Long userid,Long crlid)throws Exception;

    int updateClassCurrencyWatchById(Long id)throws Exception;
}
