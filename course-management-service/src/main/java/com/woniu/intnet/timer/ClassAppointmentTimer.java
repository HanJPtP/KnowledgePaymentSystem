package com.woniu.intnet.timer;


import com.woniu.intnet.web.client.UserInfoClientToGetUserId;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.outnet.dao.pojo.TimeToSendEmail;
import com.woniu.service.impl.ClassAppointmentServiceImpl;
import com.woniu.service.impl.ClassEmailConfigureInfoServiceImpl;
import com.woniu.service.impl.TimeToSendEmailServiceImpl;
import com.woniu.util.MailUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ClassAppointmentTimer {
    //原本发邮件业务逻辑层
    @Resource
    private ClassAppointmentServiceImpl classAppointmentService;
    //改进版邮件的业务逻辑层
    @Resource
    private  TimeToSendEmailServiceImpl timeToSendEmailService;
    @Resource
    private ClassEmailConfigureInfoServiceImpl classEmailConfigureInfoService;

    //获得用户微服务的方法
    @Resource
    private UserInfoClientToGetUserId userInfoClient;

    /**
     * 定时发邮件基础版本
     * 如果一个人预约多个直播,在同一个10分装段内,无法给出对应的回应
     */
//    @SneakyThrows
//    @Async("AsyncFrist")
//    @Scheduled(fixedDelay=10000)
//    protected void sendEmailRegularly(){
//        List<ClassAppointment> n = classAppointmentService.listClassAppointmentBySendOrNotAndTime("n", new Date());
//        if(n.size()>0){
//            List<Long> userids = new ArrayList<>();
//            List<Long> ids = new ArrayList<>();
//            for (ClassAppointment classAppointment : n) {
//                //查用户用的集合
//                userids.add(classAppointment.getUserid());
//                //修改用的id
//                ids.add(classAppointment.getId());
//            }
//            //群发邮件
//            ResponseResult<Object> emailByUid = null;
//            try {
//                emailByUid=userInfoClient.getEmailByUid(userids);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            List<String> data = (List<String>) emailByUid.getData();
//            //修改状态存的id
//            classAppointmentService.updateClassAppointmentByIdToSendOrNotIsY(ids);
//            String join = StringUtils.join(data, ",");
//            mailUtils.sendHtmlMail(join,"关注的主播即将开播", "<h1>还有10分钟开播</h1>");
//        }
//    }

    /**
     * 改进版本,可以让人知道信息,sql语句改进下多关联一张表还能知道开播人是谁
     * 但是问题是如果邮件发送失败,这个是先改的状态,不会再次读重发
     */
    @SneakyThrows
    @Async("AsyncFrist")
    @Scheduled(fixedDelay=10000)
    protected synchronized void sendEmailRegularlySenior(){
        List<TimeToSendEmail> n = timeToSendEmailService.listTimeToSendEmailByQueryWapper("n", new Date());
        //发邮件的封装的工具类
        MailUtils mailUtils = new MailUtils();
        if(mailUtils.getMailSender()==null){
            System.out.println("未查到数据");
            return;
        }
        //当集合不为空时
        if(n.size()>0){
            //遍历对象
            for (TimeToSendEmail timeToSendEmail : n) {
                List<Long> ids = new ArrayList<>();
                //获得需要修改的id的字符串,用","拼接的
                String id = timeToSendEmail.getId();
                String[] split = id.split(",");
                //得到需要修改的id封装到一个list集合
                for (String s : split) {
                    ids.add(Long.parseLong(s));
                }
                //需要获得邮箱的用户id(由于sql是以那个用户关注的分组,userid不会有重复的)
                Long userid = timeToSendEmail.getUserid();
                List<Long> uid = new ArrayList<>();
                uid.add(userid);
                System.out.println(uid.toString());
                //获得对应用户的邮箱
                try {
                    ResponseResult<Object> emailByUid = userInfoClient.getEmailByUid(uid);
                    if(emailByUid.getData()==null)
                        continue;
                    List<String> data = (List<String>)emailByUid.getData();
                    String[] join = data.stream().toArray(String[]::new);
                    //发邮件

                    mailUtils.sendHtmlMailByBcc(join,"关注的课程即将开播", "<h1>您与预约观看的"+timeToSendEmail.getName()+"还有10分钟开播</h1>");
                    classAppointmentService.updateClassAppointmentByIdToSendOrNotIsY(ids);
                }catch (Exception e){
                    log.error("发送错误");
                }
            }
        }
    }


}
