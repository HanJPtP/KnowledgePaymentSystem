package com.woniu.users.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import com.woniu.users.inlet.web.client.UserStatusClient;
import com.woniu.users.inlet.web.dto.UserDetailDto;
import com.woniu.users.inlet.web.dto.UserMsg;
import com.woniu.users.outlet.dao.mysql.mapper.*;
import com.woniu.users.outlet.dao.mysql.po.*;
import com.woniu.users.service.IUserInfoService;
import com.woniu.users.util.PageInfo;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoServiceImpl implements IUserInfoService {

    private final UserInfoMapper userInfoMapper;

    private final UserPayLogMapper userPayLogMapper;

    private final UserAddrMapper userAddrMapper;

    private final UserLevelMapper userLevelMapper;

    private final LevelupGiftMapper levelupGiftMapper;

    private final UserCouponMapper userCouponMapper;

    private final UserMemberInfoMapper userMemberInfoMapper;

    private final UserPointLogMapper userPointLogMapper;

    private final UserStatusClient userStatusClient;





    @Override
    public UserInfo getUserByUid(Long uid) {
        return userInfoMapper.getUserByUid(uid);
    }

    /**
     *  用户多条件查询
     * @param pageNo
     * @param pageSize
     * @param levelName
     * @param startRegisterTime
     * @param endRegisterTime
     * @param labelName
     * @param uid
     * @param username
     * @param phone
     * @return
     */
    @Override
    public PageInfo<UserMsg> listUserInfo(Long pageNo,Long pageSize, String levelName, String startRegisterTime,
                                          String endRegisterTime, String[] labelName, Long uid, String username, String phone) {
        Page page = new Page(pageNo,pageSize);
        IPage iPage = userInfoMapper.listUserInfo(page, levelName, startRegisterTime, endRegisterTime, labelName, uid, username, phone);
        PageInfo<UserMsg> pageInfo = new PageInfo<UserMsg>(iPage);
        for (UserMsg u : pageInfo.getData()){
            List<UserPayLog> userPayLogs = userPayLogMapper.getAmountList(u.getUid());
            // 用户状态和注册时间
            ResponseResult<UserLoginToUserDto> result = userStatusClient.getUserInfoItemById(u.getUid());
            System.out.println("---result--- + " + result);
            if(result.getData() != null) {
                UserLoginToUserDto udata = result.getData();
                // 用户状态
                if(u.getStatus() != null) {
                    u.setStatus(udata.getStatus());
                }

                if(udata.getRegisterTime() != null) {
                    // 注册时间
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    u.setRegisterTime(sdf.format(udata.getRegisterTime()));
                }
            }

            if(userPayLogs.size() > 0) {
                //循环 添加消费信息
                // 总消费次数
                u.setPayTimes((long) userPayLogs.size());

                // 总消费
                u.setTotalPay((double) 0);
                for (UserPayLog up : userPayLogs) {
                    u.setTotalPay(u.getTotalPay() + up.getAmount());
                }

            }

        }
        return pageInfo;
    }

    /**
     *  根据uid集合查询email集合
     * @param uidList
     * @return
     */
    @Override
    public List<String> getEmailByUid(List<Long> uidList) {

        return userInfoMapper.getEmailByUid(uidList);
    }


    /**
     *  查询用户 的基本消息
     * @param uid
     * @return
     */
    @Override
    public UserDetailDto getUserDetailByUid(Long uid) {
        // 得到用户信息
        UserInfo userInfo = userInfoMapper.getUserByUid(uid);
        // 得到用户地址信息 地址没有的值需要是""，不能为空
        UserAddr userAddr = userAddrMapper.getByUid(uid);
        // 给UserDetailDto对象赋值
        UserDetailDto userDetailDto =
                new UserDetailDto(userInfo.getUid(),userInfo.getUsername(),userInfo.getGender(),
                        userInfo.getUserImg(),userInfo.getAge(),userInfo.getPhone(),
                        userInfo.getEmail(),userInfo.getJob(),userInfo.getSourceWay(),userInfo.getRegisterTime(),
                        new Date(),userInfo.getNote(),userAddr.getConsignee(),
                        userAddr.getCountry()+userAddr.getProvince()+userAddr.getCity()+userAddr.getDistrict()+userAddr.getAddress());

        // 返回里，没有最后登录时间
        return userDetailDto;
    }

    /**
     *  新增用户信息
     * @param userInfo
     * @return
     */
    @Override
    public int addUserInfo(UserInfo userInfo) {
        // 根据邮箱查询，避免重复新增
        List<UserInfo> userInfos = userInfoMapper.selectByEmail(userInfo.getEmail());
        if(userInfos.size() <= 0){
            // 非空验证

            // 新增用户信息
            userInfo.setUid(new SnowFlakeGenerator(1,5).nextId());
            userInfo.setModifiedTime(new Date());
            userInfoMapper.addUserInfo(userInfo);
            // 新增用户免费等级信息，等级默认升级为1级
            UserMemberInfo memberInfo = new UserMemberInfo(userInfo.getUid(), 1L, "", 1, 0, 0, new Date());

            // 得到 当前成长值对应的等级对象
            List<UserLevel> userLevelList = userLevelMapper.getUserLevelByGrowthNum(0);
            UserLevel userLevel = userLevelList.get(0);
            if(userLevel == null){
                userLevel.setLevelId(1L);
            }

            // 如果发生等级变化，修改等级，赠送升级礼物，修改状态
            // 发放升级礼物
            // 根据礼包类型id查询升级礼包对象
            LevelupGift levelupGift = levelupGiftMapper.getByGiftId(userLevel.getLevelId());
            // 新增赠送的积分
            Integer points = memberInfo.getPoints() + levelupGift.getPoints();
            memberInfo.setPoints(points);
            // 新增积分日志表
            UserPointLog pointLog = new UserPointLog(null, memberInfo.getUid(), "用户注册赠送", null, levelupGift.getPoints(), new Date());
            userPointLogMapper.addUserPointLog(pointLog);

            // 新增用户优惠卷信息
            UserCoupon userCoupon = new UserCoupon(memberInfo.getUid(), levelupGift.getCouponid(), 0);
            userCouponMapper.addUserCoupon(userCoupon);
            // 修改会员等级信息
            memberInfo.setLevelId(userLevel.getLevelId());
            // 改变礼物状态为已发放
            memberInfo.setGiftStatus(0);
            // 新增会员等级信息
            userMemberInfoMapper.addUserMemberInfo(memberInfo);
            return 1;
        }

        return 0;
    }
}
