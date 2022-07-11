package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.intnet.web.vo.WatchUserTimeByLiveVo;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outnet.dao.mysql.ClassLiveWatchMapper;
import com.woniu.outnet.dao.mysql.ClassRecordLiveMapper;
import com.woniu.outnet.dao.pojo.ClassLiveWatch;
import com.woniu.outnet.dao.pojo.ClassRecordLive;
import com.woniu.service.IClassLiveWatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ClassLiveWatchServiceImpl implements IClassLiveWatchService {

    private final ClassLiveWatchMapper classLiveWatchMapper;

    private final ClassRecordLiveMapper classRecordLiveMapper;

//    private final SnowFlakeGenerator snowFlakeGenerator;

    private SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(1, 24);

    /**
     * 根据用户id查询看了什么课多长时间几次
     *
     * @param userid
     * @return
     * @throws Exception
     */
    @Override
    public List<WatchUserTimeByLiveVo> listClassLiveWatchByUserid(Long userid) throws Exception {
        List<ClassLiveWatch> classLiveWatches = classLiveWatchMapper.listClassLiveWatchByUserid(userid);

        List<WatchUserTimeByLiveVo> watchUserTimeByLiveVos = new ArrayList<>();
        for (ClassLiveWatch classLiveWatch : classLiveWatches) {
            if (classRecordLiveMapper.selectById(classLiveWatch.getCrlid()) != null) {
                WatchUserTimeByLiveVo watchUserTimeByLiveVo = classLiveWatch.MakeClassLiveWatchToWatchUserTimeByLiveVo(new WatchUserTimeByLiveVo(), classLiveWatch);
                watchUserTimeByLiveVos.add(watchUserTimeByLiveVo);
            }
        }
        return watchUserTimeByLiveVos;
    }

    /**
     * 用户进来的时候需要先查该直播间的状态,当为正在直播才算有效时长,不然不添加
     *
     * @param userid
     * @param crlid
     * @return
     * @throws Exception
     */
    @Override
    public Long insertClassLiveWatchWhileIn(Long userid, Long crlid) throws Exception {
        QueryWrapper<ClassLiveWatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid).eq("crlid", crlid).isNull("quittime").isNotNull("firsttime");
        List<ClassLiveWatch> classLiveWatches = classLiveWatchMapper.selectList(queryWrapper);
        if (classLiveWatches.size() > 0) {
            return -2l;
        }
        ClassRecordLive classRecordLive = classRecordLiveMapper.selectById(crlid);
        if (classRecordLive.getLiveStatus().equals("2")) {
            ClassLiveWatch classLiveWatch = new ClassLiveWatch();
            classLiveWatch.setId(snowFlakeGenerator.nextId());
            classLiveWatch.setCrlid(crlid);
            classLiveWatch.setUserid(userid);
            classLiveWatch.setFirsttime(new Date());
            int i = classLiveWatchMapper.insertClassLiveWatchWhileIn(classLiveWatch);
            Long id = 0l;
            if (i > 0) {
                id = classLiveWatch.getId();
            }
            return id;
        } else {
            return -1l;
        }
    }

    /**
     * 修改当前观看时长
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassLiveWatchById(Long id) throws Exception {
        QueryWrapper<ClassLiveWatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("quittime").eq("id", id);
        ClassLiveWatch classLiveWatch = classLiveWatchMapper.selectOne(queryWrapper);
        if (classLiveWatch != null) {
            return 0;
        }
        ClassLiveWatch classLiveWatchById = classLiveWatchMapper.getClassLiveWatchById(id);
        int i = 0;
        if (classLiveWatchById != null && classLiveWatchById.getQuittime() == null && classLiveWatchById.getWatchtime() == null) {
            classLiveWatchById.setQuittime(new Date());
            classLiveWatchById.setWatchtime(classLiveWatchById.getQuittime().getTime() - classLiveWatchById.getFirsttime().getTime());
            i = classLiveWatchMapper.updateClassLiveWatchById(classLiveWatchById);
        }
        return i;
    }

    @Override
    public Long getTotleByCrlid(Long crlid) throws Exception {
        return classLiveWatchMapper.getTotleByCrlid(crlid);
    }


}
