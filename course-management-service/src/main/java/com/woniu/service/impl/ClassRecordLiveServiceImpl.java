package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.intnet.web.fo.ClassRecordLiveFo;
import com.woniu.intnet.web.fo.ClassRecordLiveUpdateFo;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outnet.dao.elasticserch.ClassRecordLiveRepository;
import com.woniu.outnet.dao.mysql.ClassRecordLiveMapper;
import com.woniu.outnet.dao.pojo.ClassRecordLive;
import com.woniu.outnet.dao.pojo.PageInfo;
import com.woniu.service.IClassRecordLiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
@RequiredArgsConstructor
public class ClassRecordLiveServiceImpl implements IClassRecordLiveService {

    private final ClassRecordLiveMapper classRecordLiveMapper;

    private final ClassRecordLiveRepository classRecordLiveRepository;

    private SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(24, 2);

    /**
     * 多条件分页查询(直播)
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @param starttime
     * @param endtime
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<ClassRecordLive> listAllClassRecordLiveByChooseAndPage(Integer pageNo,
                                                                           Integer pageSize,
                                                                           String name,
                                                                           String starttime,
                                                                           String endtime) throws Exception {
        String n = "*";
        String sT = null;
        String eT = null;
        if (name.length() > 0) {
            n = name;
        }
        if (starttime.length() > 0) {
            sT = starttime;
        }
        if (endtime.length() > 0) {
            eT = endtime;
        }
        PageInfo<ClassRecordLive> pageInfoVo = null;
        //先从es中查询
        try {
            org.springframework.data.domain.Page<ClassRecordLive> estarttiem = classRecordLiveRepository.findByPage2(n, sT, eT, PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, "estarttime"));
            pageInfoVo = new PageInfo<ClassRecordLive>(estarttiem);
        } catch (Exception e) {

        }
        if (pageInfoVo == null) {

                QueryWrapper<ClassRecordLive> queryWrapper = new QueryWrapper<>();
                if (name.length() > 0) {
                    queryWrapper.like("name", name);
                }
                if (starttime.length() > 0) {
                    queryWrapper.gt("starttime", starttime);
                }
                if (endtime.length() > 0) {
                    queryWrapper.lt("endtime", endtime);
                }
                queryWrapper.orderByDesc("estarttime");
                Page<ClassRecordLive> page = new Page<ClassRecordLive>(pageNo, pageSize);
                Page<ClassRecordLive> classRecordLivePage = classRecordLiveMapper.listAllClassLiveWatchByChooseAndPage(page, queryWrapper);
                PageInfo<ClassRecordLive> pageInfo = new PageInfo<>(classRecordLivePage);
                if (pageInfo.getData().size() > 0) {
                    classRecordLiveRepository.saveAll(pageInfo.getData());
                }
                return pageInfo;
        }
        return pageInfoVo;
    }

    /**
     * 添加直播
     *
     * @param classRecordLiveFo
     * @return
     * @throws Exception
     */
    @Override
    public int insertClassRecordLive(ClassRecordLiveFo classRecordLiveFo) throws Exception {
        ClassRecordLive classRecordLive = new ClassRecordLive();
        classRecordLive.setEstarttime(classRecordLiveFo.getEstarttime());
        //直播状态
        classRecordLive.setLiveStatus("1");
        //该数据状态
        classRecordLive.setStatus("1");
        classRecordLive.setImg(classRecordLiveFo.getImg());
        classRecordLive.setUserid(classRecordLiveFo.getUserid());
        classRecordLive.setName(classRecordLiveFo.getName());
        classRecordLive.setId(snowFlakeGenerator.nextId());
        //新增
        int insert = classRecordLiveMapper.insert(classRecordLive);
        if (insert > 0) {
            classRecordLiveRepository.save(classRecordLive);
        }
        return insert;
    }

    /**
     * 开始直播
     *
     * @param startTime
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassRecordLiveWhileBeginShow(Long id, Date startTime) throws Exception {
        ClassRecordLive recordLive = classRecordLiveMapper.selectById(id);
        if(recordLive==null){
            return -1;
        }
        recordLive.setStarttime(startTime);
        int i = classRecordLiveMapper.updateById(recordLive);
        if (i > 0) {
            classRecordLiveRepository.save(recordLive);
        }
        return i;
    }

    /**
     * 结束直播
     *
     * @param endTime
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassRecordLiveWhileEndShow(Long id, Date endTime) throws Exception {
        ClassRecordLive recordLive = classRecordLiveMapper.selectById(id);
        if(recordLive==null){
            return -1;
        }
        if(recordLive.getStarttime()==null){
            return -2;
        }
        recordLive.setEndtime(endTime);
        int i = classRecordLiveMapper.updateById(recordLive);
        if (i > 0) {
            classRecordLiveRepository.save(recordLive);
        }
        return i;
    }

    /**
     * 修改该数据状态
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassRecordLiveStatusById(Long id, String status) throws Exception {
        ClassRecordLive recordLive = classRecordLiveMapper.selectById(id);
        recordLive.setStatus(status);
        int i = classRecordLiveMapper.updateById(recordLive);
        if (i > 0) {
            classRecordLiveRepository.save(recordLive);
        }
        return i;
    }

    /**
     * 修改任何数据同个id
     *
     * @param classRecordLiveUpdateFo
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassRecordLiveAll(ClassRecordLiveUpdateFo classRecordLiveUpdateFo) throws Exception {
        ClassRecordLive classRecordLive = new ClassRecordLive(classRecordLiveUpdateFo);
        int update = classRecordLiveMapper.update(null, Wrappers.<ClassRecordLive>lambdaUpdate()
                .set(ClassRecordLive::getUserid, classRecordLive.getUserid())
                .set(ClassRecordLive::getEstarttime, classRecordLive.getEstarttime())
                .set(ClassRecordLive::getLiveStatus, classRecordLive.getLiveStatus())
                .set(ClassRecordLive::getImg, classRecordLive.getImg())
                .set(ClassRecordLive::getStatus, classRecordLive.getStatus())
                .set(ClassRecordLive::getEndtime, classRecordLive.getEndtime())
                .set(ClassRecordLive::getName, classRecordLive.getName())
                .set(ClassRecordLive::getNum, classRecordLive.getNum())
                .set(ClassRecordLive::getOrdernum, classRecordLive.getOrdernum())
                .set(ClassRecordLive::getStarttime, classRecordLive.getStarttime())
                .eq(ClassRecordLive::getId, classRecordLive.getId()));
        //修改成功
        if (update > 0) {
            ClassRecordLive recordLive = classRecordLiveMapper.selectById(classRecordLive.getId());
            classRecordLiveRepository.save(recordLive);
        }
        return update;
    }


}
