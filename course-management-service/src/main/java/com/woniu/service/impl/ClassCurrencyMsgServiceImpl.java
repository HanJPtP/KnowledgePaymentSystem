package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.intnet.web.fo.*;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outnet.dao.elasticserch.ClassCurrencyMsgRepository;
import com.woniu.outnet.dao.mysql.ClassCurrencyMsgMapper;
import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;
import com.woniu.outnet.dao.pojo.PageInfo;
import com.woniu.service.IClassCurrencyMsgService;
import com.woniu.util.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClassCurrencyMsgServiceImpl implements IClassCurrencyMsgService {


    private final ClassCurrencyMsgMapper classCurrencyMsgMapper;

    private final ClassCurrencyMsgRepository classCurrencyMsgRepository;

    private final ScheduledExecutorService scheduledExecutorService;

    private final RabbitTemplate rabbitTemplate;

    private SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(24, 3);

    /**
     * 分页多条件查询(通用课程)
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @param startPrice
     * @param endPrice
     * @param starttime
     * @param endtime
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<ClassCurrencyMsg> listClassCurrencyMsgByChooseAndPage(Integer pageNo,
                                                                          Integer pageSize,
                                                                          String name,
                                                                          Double startPrice,
                                                                          Double endPrice,
                                                                          String starttime,
                                                                          String endtime) throws Exception {
        String n = "*";
        Double sP = null;
        Double eP = null;
        String sT = null;
        String eT = null;
        if (name.length() > 0) {
            n = name;
        }
        if (startPrice >= 0) {
            sP = startPrice;
        }
        if (endPrice >= 0) {
            eP = endPrice;
        }
        if (starttime.length() > 0) {
            sT = starttime;
        }
        if (endtime.length() > 0) {
            eT = endtime;
        }
        PageInfo<ClassCurrencyMsg> pageInfoVo = null;
        //现在es查
        try {
            org.springframework.data.domain.Page<ClassCurrencyMsg> groundingtime = classCurrencyMsgRepository.fingByPage(n, sP, eP, sT, eT, PageRequest.of(pageNo - 1, pageSize, Sort.Direction.DESC, "groundingtime"));
            pageInfoVo = new PageInfo<ClassCurrencyMsg>(groundingtime);
        }catch (Exception e){

        }
        //没有再在数据库查
        if (pageInfoVo== null) {
            Page<ClassCurrencyMsg> page = new Page<>(pageNo, pageSize);
            QueryWrapper<ClassCurrencyMsg> queryWrapper = new QueryWrapper<>();
            if (name.length() > 0) {
                queryWrapper.like("name", name);
            }
            if (startPrice != null) {
                queryWrapper.gt("privce", startPrice);
            }
            if (endPrice != null) {
                queryWrapper.gt("privce", endPrice);
            }
            if (starttime.length() > 0) {
                queryWrapper.gt("groundingtime", starttime);
            }
            if (endtime.length() > 0) {
                queryWrapper.gt("groundingtime", endPrice);
            }
            Page<ClassCurrencyMsg> page1 = classCurrencyMsgMapper.listClassCurrencyMsgByChooseAndPage(page, queryWrapper);
            PageInfo<ClassCurrencyMsg> pageInfo = new PageInfo<>(page1);
            classCurrencyMsgRepository.saveAll(pageInfo.getData());
            return pageInfo;
        }
        return pageInfoVo;
    }

    /**
     * 根据id查询课程
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ClassCurrencyMsg listClassCurrencyMsgById(Long id) throws Exception {
        ClassCurrencyMsg classCurrencyMsg = classCurrencyMsgMapper.listClassCurrencyMsgById(id);
        if(classCurrencyMsg!=null){
            classCurrencyMsgRepository.save(classCurrencyMsg);
        }else{
            classCurrencyMsgRepository.deleteById(classCurrencyMsg.getId());
        }
        return classCurrencyMsg;
    }

    /**
     * 根据id修改状态
     *
     * @param id
     * @param status
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassCurrencyMsgStatus(Long id, String status) throws Exception {
        ClassCurrencyMsg classCurrencyMsg = new ClassCurrencyMsg();
        classCurrencyMsg.setId(id);
        classCurrencyMsg.setStatus(status);
        int i = classCurrencyMsgMapper.updateById(classCurrencyMsg);
        if (i > 0) {
            ClassCurrencyMsg classCurrencyMsg1 = classCurrencyMsgMapper.selectById(id);
            classCurrencyMsgRepository.save(classCurrencyMsg1);
        }
        return i;
    }

    /**
     * 新增功能
     *
     * @param classCurrencyMsgAddFo
     * @return
     * @throws Exception
     */
    @Override
    public int insertClassCurrencyMsg(ClassCurrencyMsgAddFo classCurrencyMsgAddFo) throws Exception {
        ClassCurrencyMsg classCurrencyMsg = new ClassCurrencyMsg(classCurrencyMsgAddFo);
        classCurrencyMsg.setId(snowFlakeGenerator.nextId());
        int insert = classCurrencyMsgMapper.insert(classCurrencyMsg);
        if (insert > 0) {
            ClassCurrencyMsg classCurrencyMsg1 = classCurrencyMsgMapper.selectById(classCurrencyMsg.getId());
            classCurrencyMsgRepository.save(classCurrencyMsg1);
        }
        return insert;
    }

    @Override
    public int updateClassCurrencyMsgAllCanNull(ClassCurrencyMsgUpdateFo classCurrencyMsgUpdateFo) throws Exception {
        ClassCurrencyMsg classCurrencyMsg = new ClassCurrencyMsg();
        BeanUtils.copyProperties(classCurrencyMsgUpdateFo, classCurrencyMsg);
        int update = classCurrencyMsgMapper.update(null, Wrappers.<ClassCurrencyMsg>lambdaUpdate()
                .set(ClassCurrencyMsg::getEndtime, classCurrencyMsg.getEndtime())
                .set(ClassCurrencyMsg::getImg, classCurrencyMsg.getImg())
                .set(ClassCurrencyMsg::getName, classCurrencyMsg.getName())
                .set(ClassCurrencyMsg::getStarttime, classCurrencyMsg.getStarttime())
                .set(ClassCurrencyMsg::getOrdernum, classCurrencyMsg.getOrdernum())
                .set(ClassCurrencyMsg::getStatus, classCurrencyMsg.getStatus())
                .set(ClassCurrencyMsg::getAddress, classCurrencyMsg.getAddress())
                .set(ClassCurrencyMsg::getAudition, classCurrencyMsg.getAudition())
                .set(ClassCurrencyMsg::getCcid, classCurrencyMsg.getCcid())
                .set(ClassCurrencyMsg::getCstatus, classCurrencyMsg.getCstatus())
                .set(ClassCurrencyMsg::getDescribes, classCurrencyMsg.getDescribes())
                .set(ClassCurrencyMsg::getEnclosure, classCurrencyMsg.getEnclosure())
                .set(ClassCurrencyMsg::getGroundingtime, classCurrencyMsg.getGroundingtime())
                .set(ClassCurrencyMsg::getOffshelftime, classCurrencyMsg.getOffshelftime())
                .set(ClassCurrencyMsg::getPrivce, classCurrencyMsg.getPrivce())
                .set(ClassCurrencyMsg::getStock, classCurrencyMsg.getStock())
                .set(ClassCurrencyMsg::getUserid, classCurrencyMsg.getUserid())
                .eq(ClassCurrencyMsg::getId, classCurrencyMsg.getId()));
        if (update > 0) {
            ClassCurrencyMsg classCurrencyMsg1 = classCurrencyMsgMapper.selectById(classCurrencyMsg.getId());
            classCurrencyMsgRepository.save(classCurrencyMsg1);
        }

        return update;
    }

    /**
     * 修改定时上架时间
     *
     * @param id
     * @param startTime
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassCurrencyMsgStartTimeById(Long id, String startTime) throws Exception {
        ClassCurrencyMsg classCurrencyMsg = classCurrencyMsgMapper.selectById(id);
        int i = 0;
        if (classCurrencyMsg == null) {
            classCurrencyMsgRepository.deleteById(id);
            return -1;//未查到该数据
        }
        if (startTime.length() == 0) {
            ClassCurrencyMsgUpdateFo classCurrencyMsgUpdateFo = new ClassCurrencyMsgUpdateFo();
            BeanUtils.copyProperties(classCurrencyMsg, classCurrencyMsgUpdateFo);
            classCurrencyMsgUpdateFo.setStarttime(null);
            i = this.updateClassCurrencyMsgAllCanNull(classCurrencyMsgUpdateFo);
            if(i>0){
                classCurrencyMsgRepository.save(classCurrencyMsg);
            }
        }
        Date date = DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", startTime);
        if (classCurrencyMsg.getEndtime() != null) {
            if (date.getTime() >= (classCurrencyMsg.getEndtime().getTime() - 3600000l) && date.getTime() <= (classCurrencyMsg.getEndtime().getTime() + 360000l)) {
                return -2;//定时上架时间不能太接近定时下架时间
            }
            classCurrencyMsg.setStarttime(date);
            i=classCurrencyMsgMapper.updateById(classCurrencyMsg);
            if(i>0){
                classCurrencyMsgRepository.save(classCurrencyMsg);
            }
        }
        return i;
    }

    /**
     * 修改定时下架时间
     *
     * @param id
     * @param endTime
     * @return
     * @throws Exception
     */
    @Override
    public int updateClassCurrencyMsgEndTimeById(Long id, String endTime) throws Exception {
        ClassCurrencyMsg classCurrencyMsg = classCurrencyMsgMapper.selectById(id);
        int i = 0;
        if (classCurrencyMsg == null) {
            classCurrencyMsgRepository.deleteById(id);
            return -1;//未查到该数据
        }
        if (endTime.length() == 0) {
            ClassCurrencyMsgUpdateFo classCurrencyMsgUpdateFo = new ClassCurrencyMsgUpdateFo();
            BeanUtils.copyProperties(classCurrencyMsg, classCurrencyMsgUpdateFo);
            classCurrencyMsgUpdateFo.setEndtime(null);
            i = this.updateClassCurrencyMsgAllCanNull(classCurrencyMsgUpdateFo);
            if(i>0){
                classCurrencyMsgRepository.save(classCurrencyMsg);
            }
        }
        Date date = DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", endTime);
        if (classCurrencyMsg.getStarttime() != null) {
            if (date.getTime() >= (classCurrencyMsg.getStarttime().getTime() - 3600000l) && date.getTime() <= (classCurrencyMsg.getStarttime().getTime() + 360000l)) {
                return -2;//定时上架时间不能太接近定时下架时间
            }
            classCurrencyMsg.setEndtime(date);
            i=classCurrencyMsgMapper.updateById(classCurrencyMsg);
            if(i>0){
                classCurrencyMsgRepository.save(classCurrencyMsg);
            }
        }
        return i;
    }

    /**
     * 实现定时上下架的功能
     * @throws Exception
     */
    @Override
    public void timedUpperAndLowerShelves() throws Exception {
        //获得当前时间
        Date date = new Date();
        //获得需要上架和下架的对象(1小时内的)
        //上架的对象
        List<ClassCurrencyMsg> classCurrencyMsgsS = classCurrencyMsgMapper.listClassCurrencyMsgByStartTime(date);
        System.out.println(classCurrencyMsgsS.toString());
        //下架的
        List<ClassCurrencyMsg> classCurrencyMsgsE = classCurrencyMsgMapper.listClassCurrencyMsgByEndTime(date);
        System.out.println(classCurrencyMsgsE.toString());
        //遍历集合
        //遍历上架的
        for (ClassCurrencyMsg currencyMsgsS : classCurrencyMsgsS) {
            Date starttime = currencyMsgsS.getStarttime();
            if(starttime.getTime()-date.getTime()>=1800000l){
                //就放到延迟队列中去
                log.info("执行定时的延迟线程任务");
                scheduledExecutorService.schedule(new RabbitMQSendMsgFo("class.first.exchange","class.first.routing.key",currencyMsgsS.getId()+":"+"1",rabbitTemplate),starttime.getTime()-date.getTime()-1800000l,TimeUnit.MILLISECONDS);
            }else{
                //放到延迟线程中去执行
                log.info("多久后执行{}",starttime.getTime()-date.getTime());
                scheduledExecutorService.schedule(new TimedUpperFo(this,currencyMsgsS), starttime.getTime()-date.getTime(), TimeUnit.MILLISECONDS);
            }
        }
        //遍历下架的
        for (ClassCurrencyMsg currencyMsgsE : classCurrencyMsgsE) {
            Date endtime = currencyMsgsE.getEndtime();
            if(endtime.getTime()-date.getTime()>=1800000l){
                //放到延迟队列中去
                log.info("执行定时的延迟线程任务");
                scheduledExecutorService.schedule(new RabbitMQSendMsgFo("class.first.exchange","class.first.routing.key",currencyMsgsE.getId()+":"+"2",rabbitTemplate),endtime.getTime()-date.getTime()-1800000l,TimeUnit.MILLISECONDS);
            }else{
                //放到延迟容器中去
                log.info("多久后执行{}",endtime.getTime()-date.getTime());
                scheduledExecutorService.schedule(new LowerSherlesFo(this,currencyMsgsE), endtime.getTime()-date.getTime() ,TimeUnit.MILLISECONDS);
            }
        }
    }
}
