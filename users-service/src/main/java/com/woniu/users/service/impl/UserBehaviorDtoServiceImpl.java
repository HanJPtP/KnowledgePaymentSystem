package com.woniu.users.service.impl;

import com.woniu.users.inlet.web.dto.UserBehaviorDto;
import com.woniu.users.outlet.dao.mysql.mapper.UserLearnMsgMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserPayLogMapper;
import com.woniu.users.outlet.dao.mysql.po.UserLearnMsg;
import com.woniu.users.outlet.dao.mysql.po.UserPayLog;
import com.woniu.users.service.IUserBehaviorDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserBehaviorDtoServiceImpl implements IUserBehaviorDtoService {

    private final UserPayLogMapper userPayLogMapper;

    private final UserLearnMsgMapper userLearnMsgMapper;

    /**
     *  根据uid得到UserBehaviorDto对象
     * @param uid
     * @return
     */
    @Override
    public UserBehaviorDto getUserBehaviorByUid(Long uid) {
        UserBehaviorDto userBehaviorDto = new UserBehaviorDto();
        // 得到下单数量和下单金额,最近消费时间
        Integer payTimes = 0;
        Double totalPay = 0.0;
        Date currentTime = null;

        List<UserPayLog> userPayLogs = userPayLogMapper.listUserPayLog(uid);

        if(userPayLogs.size() > 0) {
            // 定义最近消费时间
            currentTime = userPayLogs.get(0).getCreateTime();

            //循环 添加消费信息
            // 总消费次数
            payTimes = userPayLogs.size();
            // 总消费
            for (UserPayLog up : userPayLogs) {
                totalPay += up.getAmount();

                // 比较时间时间
                if(up.getCreateTime().compareTo(currentTime) >=0 ){
                    // 如果时间比currentTime大或等于
                    currentTime = up.getCreateTime();
                }
            }

        }
        // 得到UserLearnMsg对象
        UserLearnMsg userLearnMsg = userLearnMsgMapper.getUserLearnMsgByUid(uid);
        // 给userBehaviorDto对象赋值
        userBehaviorDto.setUid(uid);
        userBehaviorDto.setUserPayTimes(payTimes);
        userBehaviorDto.setUserTotalPay(totalPay);
        userBehaviorDto.setCurrentTime(currentTime);

        userBehaviorDto.setUserLearnMsg(userLearnMsg);

        return userBehaviorDto;
    }
}
