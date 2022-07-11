package com.woniu.service.impl;

import com.woniu.intnet.web.vo.TotelViewLiveVo;
import com.woniu.outnet.dao.mysql.TotelViewLiveVoMapper;
import com.woniu.outnet.dao.pojo.ClassEffectiveDuration;
import com.woniu.service.ITotleViewLiveVoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotleViewLiveVoServiceImpl implements ITotleViewLiveVoService {

    private final TotelViewLiveVoMapper totelViewLiveVoMapper;

    private final ClassEffectiveDurationServiceImpl classEffectiveDurationService;

    @Override
    public List<TotelViewLiveVo> listTotelViewLiveVoWhileUserStatusIsY() throws Exception {
        ClassEffectiveDuration classEffectiveDurationOnlyOne = classEffectiveDurationService.getClassEffectiveDurationOnlyOne();
        if (classEffectiveDurationOnlyOne.getTime() != null) {
            return totelViewLiveVoMapper.listTotelViewLiveVoWhileUserStatusIsY(classEffectiveDurationOnlyOne.getTime());
        } else {
            return null;
        }
    }
}
