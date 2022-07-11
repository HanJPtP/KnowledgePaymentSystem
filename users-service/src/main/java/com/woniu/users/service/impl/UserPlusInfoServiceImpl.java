package com.woniu.users.service.impl;

import com.woniu.users.inlet.web.dto.UserPlusInfoDto;
import com.woniu.users.outlet.dao.mysql.mapper.MemberPlusTypeMapper;
import com.woniu.users.outlet.dao.mysql.mapper.UserPlusInfoMapper;
import com.woniu.users.outlet.dao.mysql.po.MemberPlusType;
import com.woniu.users.outlet.dao.mysql.po.UserPlusInfo;
import com.woniu.users.service.IUserPlusInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPlusInfoServiceImpl implements IUserPlusInfoService {

    private final UserPlusInfoMapper userPlusInfoMapper;

    private final MemberPlusTypeMapper memberPlusTypeMapper;

    /**
     *  根据uid查询UserPlusInfoDto对象
     * @param uid
     * @return
     */
    @Override
    public UserPlusInfoDto getByUid(Long uid) {
        // 查询用户付费会员信息
        UserPlusInfo userPlusInfo = userPlusInfoMapper.getUserPlusInfoByUid(uid);
        if(userPlusInfo == null){
            // 没有查到该uid的付费会员信息
            return null;
        }
        // 根据付费会员类型id，查询对象
        MemberPlusType memberPlusType = memberPlusTypeMapper.getByPlusTypeid(userPlusInfo.getPlusTypeid());

        // 给UserPlusInfoDto赋值
        UserPlusInfoDto userPlusInfoDto = new UserPlusInfoDto(userPlusInfo.getUid(), userPlusInfo.getPlusTypeid(), userPlusInfo.getCardRemind(), userPlusInfo.getOpenTime(), userPlusInfo.getStatus(), userPlusInfo.getModifiedTime(), memberPlusType);

        return userPlusInfoDto;
    }
}
