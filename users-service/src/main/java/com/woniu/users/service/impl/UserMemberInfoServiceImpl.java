package com.woniu.users.service.impl;

import com.woniu.users.inlet.web.dto.UserMemberInfoDto;
import com.woniu.users.outlet.dao.mysql.mapper.*;
import com.woniu.users.outlet.dao.mysql.po.*;
import com.woniu.users.service.IUserMemberInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  用户信息业务类
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserMemberInfoServiceImpl implements IUserMemberInfoService {
    private final UserMemberInfoMapper userMemberInfoMapper;

    private final UserPointLogMapper userPointLogMapper;

    private final UserGrowthLogMapper userGrowthLogMapper;

    private final UserLevelMapper userLevelMapper;

    private final LevelupGiftMapper levelupGiftMapper;

    private final UserCouponMapper userCouponMapper;

    private final MemberRightsMapper memberRightsMapper;

    /**
     *  根据用户id查询用户积分
     * @param uid
     * @return
     */
    @Override
    public List<Integer> getPoints(Long uid) {
        return userMemberInfoMapper.getPoints(uid);
    }

    /**
     *  修改用户积分，改变用户积分，记录积分变动日志
     * @param uid
     * @param prepoints
     * @param nowpoints
     * @param note
     * @return
     */
    @Override
    public int updateUserMemberPoints(Long uid,
                                      Integer prepoints,
                                      Integer nowpoints,
                                      String note) {
        if(userMemberInfoMapper.selectById(uid) == null){
            return 0;

        }        try {
            // 修改用户会员积分
            // 如果nowpoints没有值的话
            if(nowpoints == 0){
                // 修改前和修改后的值一致
                nowpoints = prepoints;
            }
            userMemberInfoMapper.updateUserMemberPoints(nowpoints, uid,new Date());
            // 新增会员积分变动日志表数据
            // 得到日志表对象并赋值
            UserPointLog userPointLog = new UserPointLog(null,uid,note,null,nowpoints-prepoints,new Date());
            // 调用新增方法
            userPointLogMapper.addUserPointLog(userPointLog);
            return 1;
        }catch (Exception e){
            // 抛异常，操作失败
            log.error(e.getMessage());
            return 0;
        }

    }

    /**
     *  根据用户id查询成长值
     * @param uid
     * @return
     */
    @Override
    public List<Integer> getGrowthNum(Long uid) {
        return userMemberInfoMapper.getGrowthNum(uid);
    }

    /**
     *  修改用户成长值，改变用户成长值，记录成长值变动日志
     * @param uid
     * @param preGrowthNum
     * @param nowGrowthNum
     * @param note
     * @return
     */
    @Override
    public int updateUserMemberGrowthNum(Long uid, Integer preGrowthNum, Integer nowGrowthNum, String note) {
        if(userMemberInfoMapper.selectById(uid) == null){
            return 0;
        }
        try {
            // 修改用户会员成长值
            // 如果nowGrowthNum没有值的话
            if(nowGrowthNum == 0){
                // 修改前和修改后的值一致
                nowGrowthNum = preGrowthNum;
            }
            userMemberInfoMapper.updateUserMemberGrowthNum(nowGrowthNum, uid,new Date());
            // 新增会员成长值变动日志表数据
            // 得到日志表对象并赋值
            UserGrowthLog userGrowthLog = new UserGrowthLog(null, uid, note, null, nowGrowthNum - preGrowthNum, new Date());
            // 调用新增方法
            userGrowthLogMapper.addGrowthNumLog(userGrowthLog);
            // 判断成长值新增后，用户等级是否发生变化
            // 得到 当前成长值对应的等级对象
            List<UserLevel> userLevelList = userLevelMapper.getUserLevelByGrowthNum(nowGrowthNum);
            UserLevel userLevel = userLevelList.get(0);
            // 根据uid查询当前用户会员等级信息
            UserMemberInfo userMemberInfo = userMemberInfoMapper.getByUid(uid);
            // 判断用户等级是否发生了变化
            if(userMemberInfo.getLevelId() == userLevel.getLevelId()){
                // 会员等级levelId相等，等级未变，返回修改成功
                return 1;
            }
            // 如果发生等级变化，修改等级，赠送升级礼物，修改状态
            UserMemberInfo memberInfo = new UserMemberInfo();
                // 发放升级礼物
                // 根据礼包类型id查询升级礼包对象
            LevelupGift levelupGift = levelupGiftMapper.getByGiftId(userLevel.getLevelId());
            // 新增赠送的积分
            Integer points = userMemberInfo.getPoints() + levelupGift.getPoints();
            memberInfo.setPoints(points);
            // 新增积分日志表
            UserPointLog pointLog = new UserPointLog(null, uid, "等级升级赠送", null, levelupGift.getPoints(), new Date());
            this.userPointLogMapper.addUserPointLog(pointLog);

            // 新增用户优惠卷信息
            UserCoupon userCoupon = new UserCoupon(uid, levelupGift.getCouponid(), 0);
            userCouponMapper.addUserCoupon(userCoupon);
            // 修改会员等级信息
            memberInfo.setUid(uid);
            memberInfo.setLevelId(userLevel.getLevelId());
            // 改变礼物状态为已发放
            memberInfo.setGiftStatus(0);
            // 修改会员等级信息
            userMemberInfoMapper.updateUserMemberInfo(memberInfo);
            return 1;
        }catch (Exception e){
            // 抛异常，操作失败
            log.error(e.getMessage());
            return 0;
        }
    }

    /**
     *  根据uid查询UserMemberInfoDto对象
     * @param uid
     * @return
     */
    @Override
    public UserMemberInfoDto getUserMemberDtoByUid(Long uid) {
        // 根据用户uid查询userMemberInfo对象
        UserMemberInfo userMemberInfo = userMemberInfoMapper.getByUid(uid);
        // 根据会员级别Id查询userLevel对象
        UserLevel userLevel = userLevelMapper.getByLevelId(userMemberInfo.getLevelId());
        // 根据等级权益id集合查询，对象集合
        String rightid = userLevel.getRightid();
        // 字符串转集合，得到rightsid集合
        List<Long> rightsIds = Arrays.asList(rightid.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        // 调用查询集合方法
        List<MemberRights> memberRightsList = memberRightsMapper.listMemberRightsById(rightsIds);
        // 赋值
        UserMemberInfoDto memberInfoDto = new UserMemberInfoDto(userMemberInfo,userLevel,memberRightsList);
        return memberInfoDto;
    }

    /**
     *  查询所有优惠券信息
     * @return
     */
    @Override
    public List<UserCoupon> selectAllCoupon() {
        return userCouponMapper.selectAll();
    }
}
