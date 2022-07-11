package com.woniu.service.impl;

import com.woniu.intnet.web.vo.WatchUserTimeByCurrencyVo;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outnet.dao.mysql.ClassCurrencyMsgMapper;
import com.woniu.outnet.dao.mysql.ClassCurrencyWatchMapper;
import com.woniu.outnet.dao.pojo.ClassCurrencyWatch;
import com.woniu.service.IClassCurrencyWatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassCurrencyWatchServiceImpl implements IClassCurrencyWatchService {

    private final ClassCurrencyWatchMapper classCurrencyWatchMapper;

    private final ClassCurrencyMsgMapper classCurrencyMsgMapper;

    private final SnowFlakeGenerator snowFlakeGenerator;

    /**
     * 返回通用课程对象
     *
     * @param userid
     * @return
     */
    @Override
    public List<WatchUserTimeByCurrencyVo> listWatchUserTimeByCurrencyVoByUserid(Long userid) {
        List<ClassCurrencyWatch> classCurrencyWatches = classCurrencyWatchMapper.listClassLiveWatchByUserid(userid);
        List<WatchUserTimeByCurrencyVo> watchUserTimeByCurrencyVos = new ArrayList<>();
        for (ClassCurrencyWatch classCurrencyWatch : classCurrencyWatches) {
            if (classCurrencyMsgMapper.selectById(classCurrencyWatch.getCrlid()) != null) {
                WatchUserTimeByCurrencyVo watchUserTimeByCurrencyVo = classCurrencyWatch.MakeClassCurrencyWatchToWatchUserTimeByCurrencyVo(classCurrencyWatch);
                watchUserTimeByCurrencyVos.add(watchUserTimeByCurrencyVo);
            }
        }
        return watchUserTimeByCurrencyVos;
    }

    /**
     * 进入时候生成数据
     *
     * @param userid
     * @param crlid
     * @return
     * @throws Exception
     */
    @Override
    public Long insertClassCurrencyWatch(Long userid, Long crlid) throws Exception {
        ClassCurrencyWatch classCurrencyWatch = new ClassCurrencyWatch();
        classCurrencyWatch.setId(snowFlakeGenerator.nextId());
        classCurrencyWatch.setFirsttime(new Date());
        classCurrencyWatch.setCrlid(crlid);
        classCurrencyWatch.setUserid(userid);
        int i = classCurrencyWatchMapper.insertClassCurrencyWatch(classCurrencyWatch);
        Long id = 0l;
        if (i > 0) {
            id = classCurrencyWatch.getId();
        }
        return id;
    }

    /**
     * 出去时候修改数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassCurrencyWatchById(Long id) throws Exception {
        ClassCurrencyWatch classCurrencyWatchById = classCurrencyWatchMapper.getClassCurrencyWatchById(id);
        int i = 0;
        if (classCurrencyWatchById != null && classCurrencyWatchById.getQuittime() == null && classCurrencyWatchById.getWatchtime() == null) {
            classCurrencyWatchById.setQuittime(new Date());
            classCurrencyWatchById.setWatchtime(classCurrencyWatchById.getQuittime().getTime() - classCurrencyWatchById.getFirsttime().getTime());
            i = classCurrencyWatchMapper.updateClassCurrencyWatchById(classCurrencyWatchById);
        }
        return i;
    }
}
