package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.woniu.intnet.web.vo.ShowPeopleViewVo;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outnet.dao.mysql.ClassLiveWatchTotleMapper;
import com.woniu.outnet.dao.pojo.ClassLiveWatchTotle;
import com.woniu.service.IClassLiveWatchTotleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassLiveWatchTotleServiceImpl implements IClassLiveWatchTotleService {

    private final ClassLiveWatchTotleMapper classLiveWatchTotleMapper;

    private SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(24,1);

    /**
     * 根据修改
     *
     * @param classLiveWatchTotle
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassLiveWatchTotleByCrlid(ClassLiveWatchTotle classLiveWatchTotle) throws Exception {
        QueryWrapper<ClassLiveWatchTotle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("crlid", classLiveWatchTotle.getCrlid());
        ClassLiveWatchTotle selectOne = classLiveWatchTotleMapper.selectOne(queryWrapper);
        if (selectOne != null) {
            UpdateWrapper<ClassLiveWatchTotle> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("crlid", classLiveWatchTotle.getCrlid());
            return classLiveWatchTotleMapper.update(classLiveWatchTotle, updateWrapper);
        }else{
            classLiveWatchTotle.setId(snowFlakeGenerator.nextId());
            return classLiveWatchTotleMapper.insert(classLiveWatchTotle);
        }
    }


    /**
     * 根据直播间显示有效的
     */
    @Override
    public ShowPeopleViewVo getShowPeopleViewByCrlid(Long crlid) throws Exception {
        ClassLiveWatchTotle classLiveWatchByCrlid = classLiveWatchTotleMapper.getClassLiveWatchByCrlid(crlid);
        ShowPeopleViewVo showPeopleViewVo = null;
        if (classLiveWatchByCrlid != null) {
            showPeopleViewVo = new ShowPeopleViewVo(classLiveWatchByCrlid.getCrlid(), classLiveWatchByCrlid.getMaxnum(), classLiveWatchByCrlid.getEffectiveviewers());
        }
        return showPeopleViewVo;
    }
}
