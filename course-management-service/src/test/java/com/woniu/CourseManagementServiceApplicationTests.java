package com.woniu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.intnet.web.vo.TotelViewLiveVo;
import com.woniu.intnet.web.vo.WatchUserTimeByLiveVo;
import com.woniu.outnet.dao.elasticserch.ClassRecordLiveRepository;
import com.woniu.outnet.dao.mysql.*;
import com.woniu.outnet.dao.pojo.*;
import com.woniu.service.impl.*;
import com.woniu.util.DateFormatUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CourseManagementServiceApplicationTests {
    @Resource
    private ClassRecordLiveServiceImpl classRecordLiveService;
    @Resource
    private ClassRecordLiveMapper classRecordLiveMapper;
    @Resource
    private ClassLiveWatchMapper classLiveWatchMapper;
    @Resource
    private ClassLiveWatchServiceImpl classLiveWatchService;
    @Resource
    private ClassCurrencyWatchMapper classCurrencyWatchMapper;
    @Resource
    private ClassAppointmentMapper classAppointmentMapper;
    @Resource
    private ClassAppointmentServiceImpl classAppointmentService;
    @Resource
    private TimeToSendEmailMapper timeToSendEmailMapper;
    @Resource
    private TimeToSendEmailServiceImpl timeToSendEmailService;
    @Resource
    private TotelViewLiveVoMapper totleViewLiveVoMapper;
    @Resource
    private ClassEmailConfigureInfoServiceImpl classEmailConfigureInfoServiceImpl;
    @Resource
    private ClassRecordLiveRepository classRecordLiveRepository;
    @Resource
    private ClassCurrencyMsgMapper classCurrencyMsgMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
    }

    @SneakyThrows
    @Test
    public void show1(){
        PageInfo<ClassRecordLive> classRecordLivePageInfo = classRecordLiveService.listAllClassRecordLiveByChooseAndPage(1, 5, "", "", "");
        System.out.println(classRecordLivePageInfo.toString());
    }

    @Test
    public void show2(){
        IPage<ClassRecordLive> classRecordLivePage = classRecordLiveMapper.listAllClassLiveWatchByChooseAndPage(new Page<ClassRecordLive>(1, 5), null);
        System.out.println(classRecordLivePage.getTotal());
        System.out.println(classRecordLivePage.getPages());
    }

    @SneakyThrows
    @Test
    public void show3(){
        List<ClassLiveWatch> classLiveWatches = classLiveWatchMapper.listClassLiveWatchByUserid(1l);
//        System.out.println(classLiveWatches.toString());
        List<WatchUserTimeByLiveVo> watchUserTimeByLiveVos = classLiveWatchService.listClassLiveWatchByUserid(1l);
        System.out.println(watchUserTimeByLiveVos.toString());
    }

    @Test
    public void show4(){
        List<ClassCurrencyWatch> classCurrencyWatches = classCurrencyWatchMapper.listClassLiveWatchByUserid(1l);
        System.out.println(classCurrencyWatches.toString());
    }

    @Test
    public void show5(){
        List<Long> longs = new ArrayList<>();
        longs.add(1l);
        longs.add(2l);
        int i = classAppointmentMapper.updateClassAppointmentByListIdToChangeSendOrNot(longs);
    }


    @Test
    public void show6() throws Exception {
        List<ClassAppointment> n = classAppointmentService.listClassAppointmentBySendOrNotAndTime("n", new Date());
        if(n.size()>0){
            for (ClassAppointment classAppointment : n) {
                System.out.println(classAppointment.toString());
            }
        }else{
            System.out.println("未查到");
        }
    }

//    @Test
//    public void show7(){
//        long l = System.currentTimeMillis();
//        mailUtils.sendHtmlMail("251100172@qq.com", "主题:测试", "<h1>测试邮件是否发送成功</h1>");
//        long l1 = System.currentTimeMillis();
//        System.out.println(l1-l);
//    }
    @Test
    public void show8(){
        List<TimeToSendEmail> n = timeToSendEmailMapper.listTimeToSendEmailByQueryWapper("n", "2022-6-14 20:00:00", "2022-6-14 20:10:00");
        System.out.println(n.toString());
    }

    @SneakyThrows
    @Test
    public void show9(){
        Date date = DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss","2022-06-15 16:34:00");
        List<TimeToSendEmail> n = timeToSendEmailService.listTimeToSendEmailByQueryWapper("n", date);
        System.out.println(n);
    }
    @Test
    public void show10(){
        Date date = new Date();
        System.out.println(date.toString());
    }
    @Test
    public void show11(){
        List<TotelViewLiveVo> totelViewLiveVos = totleViewLiveVoMapper.listTotelViewLiveVoWhileUserStatusIsY(50000l);
        System.out.println(totelViewLiveVos);
    }

    @SneakyThrows
    @Test
    public void show12(){
        ClassEmailConfigureInfo classEmailConfigureInfoOnlyOne = classEmailConfigureInfoServiceImpl.getClassEmailConfigureInfoOnlyOne();
        System.out.println(classEmailConfigureInfoOnlyOne);
    }

    @SneakyThrows
    @Test
    public void show13(){

//        org.springframework.data.domain.Page<ClassRecordLive> byNameAndEstarttimeBetween = classRecordLiveRepository.findByNameAndEstarttimeBetween("*",
//                DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", "1900-01-01 00:00:00"),
//                DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", "2100-01-01 00:00:00"),
//                PageRequest.of(0, 5, Sort.Direction.DESC, "estarttime"));
//        System.out.println(byNameAndEstarttimeBetween.getContent());
        ObjectMapper objectMapper = new ObjectMapper();

//        org.springframework.data.domain.Page<ClassRecordLive> estarttime1 = classRecordLiveRepository.findByChoose("*",
//                null,
//                null,
//                PageRequest.of(0, 5, Sort.Direction.DESC, "estarttime"));
//        System.out.println(estarttime1.getContent());

        org.springframework.data.domain.Page<ClassRecordLive> estarttime = classRecordLiveRepository.findByPage2("*",
                "1990-01-01 00:00:00",
                "2010-01-01 00:00:00",
                PageRequest.of(0, 5, Sort.Direction.DESC, "estarttime"));
        PageInfo<ClassRecordLive> pageInfo = new PageInfo<ClassRecordLive>(estarttime);
        System.out.println(pageInfo);

//        org.springframework.data.domain.Page<ClassRecordLive> estarttime = classRecordLiveRepository.getClassRecordLiveByNameAndTimeBetween("*",
//                null,
//                "2100-01-01 00:00:00",
//                PageRequest.of(0, 5, Sort.Direction.DESC, "estarttime"));
//        System.out.println(estarttime.getContent());
    }

    @Test
    public void show14(){
        List<ClassCurrencyMsg> classCurrencyMsgs = classCurrencyMsgMapper.listClassCurrencyMsgByEndTime(new Date());
        System.out.println(classCurrencyMsgs.toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        List<ClassCurrencyMsg> classCurrencyMsgs1 = classCurrencyMsgMapper.listClassCurrencyMsgByEndTime(new Date());
        System.out.println(classCurrencyMsgs1.toString());
    }

    @Test
    public void show15(){
        rabbitTemplate.convertAndSend("class.first.exchange", "class.first.routing.key", "1:2");
    }
}
