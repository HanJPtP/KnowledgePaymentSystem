package com.woniu.service;

import com.woniu.outnet.dao.pojo.ClassEffectiveDuration;

public interface IClassEffectiveDurationService {

    int updateEffectiveDurationById(Long time)throws Exception;

    ClassEffectiveDuration getClassEffectiveDurationOnlyOne()throws Exception;
}
