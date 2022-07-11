package com.woniu.util;


import com.woniu.outnet.dao.pojo.ClassEmailConfigureInfo;
import com.woniu.service.impl.ClassEmailConfigureInfoServiceImpl;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Address;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Data
public class MailUtils{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //引入的依赖的使用

    private JavaMailSender mailSender;

    private String from;

    public MailUtils(){
        ClassEmailConfigureInfoServiceImpl classEmailConfigureInfoService = ApplicationContextHolder.getApplicationContext().getBean(ClassEmailConfigureInfoServiceImpl.class);
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        ClassEmailConfigureInfo classEmailConfigureInfoOnlyOne = null;
        try {
            classEmailConfigureInfoOnlyOne = classEmailConfigureInfoService.getClassEmailConfigureInfoOnlyOne();
            javaMailSender.setDefaultEncoding("UTF-8");
            javaMailSender.setHost(classEmailConfigureInfoOnlyOne.getMailhost());
            javaMailSender.setUsername(classEmailConfigureInfoOnlyOne.getMailusername());
            javaMailSender.setPassword(classEmailConfigureInfoOnlyOne.getMailpassword());
            javaMailSender.setPort(classEmailConfigureInfoOnlyOne.getMailport());
            javaMailSender.setProtocol(classEmailConfigureInfoOnlyOne.getMailprotocol());new Properties();
            Properties properties = new Properties();
            properties.setProperty("mail.stmp.auth","true");
            properties.setProperty("mail.debug","true");
            properties.setProperty("mail.stmp.timeout","2000");
            properties.setProperty("mail.stmp.port",classEmailConfigureInfoOnlyOne.getMailport()+"");
            properties.setProperty("mail.stmp.socketFactory.port",classEmailConfigureInfoOnlyOne.getMailport()+"");
            properties.setProperty("mail.stmp.socketFactory.fallback","false");
            properties.setProperty("mail.stmp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            javaMailSender.setJavaMailProperties(properties);
            this.mailSender=javaMailSender;
            this.from=classEmailConfigureInfoOnlyOne.getMailfrom();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送一般邮件(单人)
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to,String subject,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //发件人
        mailMessage.setFrom(from);
        //邮件接收人
        mailMessage.setTo(to);
        //邮件主题
        mailMessage.setSubject(subject);
        //邮件内容
        mailMessage.setText(content);
        //发送邮件
        mailSender.send(mailMessage);
    }

    /**
     * 群发邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to,String subject,String content){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = null;
        try{
            messageHelper = new MimeMessageHelper(message,true);
            //发件人
            messageHelper.setFrom(from);
            //收件人
            InternetAddress[] internetAddressesTo = InternetAddress.parse(to);
            messageHelper.setTo(internetAddressesTo);
            //邮件主题
            message.setSubject(subject);
            //邮件内容,html
            messageHelper.setText(content,true);
            //邮件发送
            mailSender.send(message);
            logger.info("邮件已经发送");
        }catch (Exception e){
            logger.error("邮件发送时发送异常"+e);
        }
    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to,String subject,String content,String filePath){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            logger.info("邮件已经发送");
        }catch (Exception e){
            logger.error("邮件发送时发送异常"+e);
        }
    }

    /**
     * 能重发的,并且过滤掉无法发送的邮箱
     * @param bcc   不显示用户
     * @param subject
     * @param content
     */
    public void sendHtmlMailByBcc(String[] bcc,String subject,String content){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =null;
        try {
            helper = new MimeMessageHelper(message,true);
            //发件人
            helper.setFrom(from);
            //收件人
            helper.setBcc(bcc);
            //主题
            helper.setSubject(subject);
            //发件内容
            helper.setText(content,true);

            mailSender.send(message);
            logger.info("成功发送邮件");
        }catch (Exception e){
            logger.info("发送邮件失败");
            //无效地址集合
            String[] invalid = getInvalidAddresses(e);
            if(invalid != null){
                sendHtmlMailByBcc(filterByArray(bcc, invalid), subject,content);
            }
        }
    }

    /**
     * 从异常获取无效地址的方法代码
     * @param e
     * @return
     */
    private static String[] getInvalidAddresses(Throwable e){
        if(e==null){
            return null;
        }
        if(e instanceof MailSendException){
            System.out.println("发送失败");
            Exception[] exceptions = ((MailSendException) e).getMessageExceptions();
            System.out.println("数组:"+exceptions.toString());
            for(Exception exception:exceptions){
                System.out.println("对象:"+exception.toString());
                if(exception instanceof SendFailedException){
                    System.out.println("细化对象:"+exception.toString());
                    return getStringAddress(((SendFailedException) exception).getInvalidAddresses());
                }
            }
        }
        if(e instanceof  SendFailedException){
            return getStringAddress(((SendFailedException) e).getInvalidAddresses());
        }
        return null;
    }

    /**
     * 上面方法的补充
     * @param address
     * @return
     */
    private static String[] getStringAddress(Address[] address){
        //无效地址集合
        ArrayList<String> invalid = new ArrayList<>();
        for (Address a : address) {
            String aa = ((InternetAddress) a).getAddress();
            //判断字符串aa不为空
            if(!StringUtils.isEmpty(aa)){
                //添加到无效地址中去
                invalid.add(aa);
            }
        }
        return invalid.stream().distinct().toArray(String[]::new);
    }

    /**
     * 将无效地址剔除替换
     * @param source
     * @param filter
     * @return
     */
    private static String[] filterByArray(String[] source,String[] filter){
        List<String> result = new ArrayList<>();
        //遍历全部地址
        for (String s : source) {
            //设置初始量
            boolean contains = false;
            //遍历无效地址
            for (String f : filter) {
                //如果含有无效地址
                if(s.contains(f)){
                    //更改状态
                    contains=true;
                    break;
                }
            }
            //如果状态为false
            if(!contains){
                //添加到无效地址中
                result.add(s);
            }
        }
        //转换成String[]集合
        return result.stream().toArray(String[]::new);
    }
}
