package com.woniu.users.inlet.web.timer;

import com.woniu.intnet.web.vo.WatchUserTimeByCurrencyVo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.users.inlet.web.client.CourseServiceClient;
import com.woniu.users.outlet.dao.mysql.mapper.UserCourseMsgMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserInfoMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserLearnMsgMapper;
import com.woniu.users.outlet.dao.mysql.po.UserCourseMsg;
import com.woniu.users.outlet.dao.mysql.po.UserLearnMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  定时更新信息
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserInfoTimer {

    private final CourseServiceClient courseServiceClient;

    private final UserInfoMapper userInfoMapper;

    private final UserLearnMsgMapper userLearnMsgMapper;

    private final UserCourseMsgMapper userCourseMsgMapper;

    /**
     *  定时获得课程学习相关信息
     */
    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void getCourseInfo(){
        List<Long> uidList = userInfoMapper.getAllUserId();
        System.out.println("uid::::" + uidList.toString());
        // 循环新增数据
        for (Long uid : uidList) {
            ResponseResult<List<WatchUserTimeByCurrencyVo>> listResponseResult = courseServiceClient.showWatchUserTimeByCurrencyVoByUserid(uid);
            if(listResponseResult.getData().size() <= 0){
                continue;
            }
            UserLearnMsg userLearnMsg = getUserLearnMsg(listResponseResult.getData());
            // 调用新增对象方法
            userLearnMsgMapper.addUserLearnMsg(userLearnMsg);

            List<UserCourseMsg> userCourseMsgList = getUserCourseMsg(listResponseResult.getData());
            // 循环新增
            for (UserCourseMsg u : userCourseMsgList){
                userCourseMsgMapper.addUserCourseMsg(u);
            }
        }
    }




    /**
     *  得到学习记录信息
     * @param list
     * @return
     */
    public UserLearnMsg getUserLearnMsg(List<WatchUserTimeByCurrencyVo> list){
        UserLearnMsg userLearnMsg = new UserLearnMsg();
        userLearnMsg.setUid(list.get(0).getUserid());
        userLearnMsg.setTotalCourse(list.size());
        // 观看毫秒时长
        Long totalTime = 0L;
        for (WatchUserTimeByCurrencyVo w : list){
            totalTime += w.getWatchtime();
        }
        // 转换成小时
        userLearnMsg.setTodayTime(totalTime/1000/60/60.0 + "h");

        return userLearnMsg;
    }

    /**
     *  得到用户学习课程信息集合
     * @param list
     * @return
     */
    public List<UserCourseMsg> getUserCourseMsg(List<WatchUserTimeByCurrencyVo> list){
        List<UserCourseMsg> courseMsgList = new ArrayList<>();

        for (WatchUserTimeByCurrencyVo w : list){
            UserCourseMsg userCourseMsg = new UserCourseMsg(w.getUserid(),w.getCcid(),w.getName(),w.getWatchtime()/1000/60/60.0+"h","",new Date());
            courseMsgList.add(userCourseMsg);
        }

        return courseMsgList;
    }
}
