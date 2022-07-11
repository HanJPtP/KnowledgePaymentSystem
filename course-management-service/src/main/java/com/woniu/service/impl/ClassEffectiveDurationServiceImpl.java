package com.woniu.service.impl;

import com.woniu.outnet.dao.mysql.ClassEffectiveDurationMapper;
import com.woniu.outnet.dao.pojo.ClassEffectiveDuration;
import com.woniu.service.IClassEffectiveDurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ClassEffectiveDurationServiceImpl implements IClassEffectiveDurationService {

    private final ClassEffectiveDurationMapper classEffectiveDurationMapper;

    /**
     * 只修改这一条数据
     * @param time
     * @return
     * @throws Exception
     */
    @Override
    public int updateEffectiveDurationById(Long time) throws Exception {
        ClassEffectiveDuration classEffectiveDurationOnlyOne = this.getClassEffectiveDurationOnlyOne();
        classEffectiveDurationOnlyOne.setTime(time);
        return classEffectiveDurationMapper.updateById(classEffectiveDurationOnlyOne);
    }

    /**
     * 数据库内容保证唯一
     * @return
     * @throws Exception
     */
    @Override
    public ClassEffectiveDuration getClassEffectiveDurationOnlyOne() throws Exception {
        return classEffectiveDurationMapper.selectOne(null);
    }
}
