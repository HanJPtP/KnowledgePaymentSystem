package com.woniu.users.service.impl;

import com.woniu.users.outlet.dao.mysql.mapper.UserLabelInfoMapper;
import com.woniu.users.outlet.dao.mysql.po.UserInfo;
import com.woniu.users.outlet.dao.mysql.po.UserLabelInfo;
import com.woniu.users.service.IUserLabelInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLabelInfoServiceImpl implements IUserLabelInfoService {

    private final UserLabelInfoMapper userLabelInfoMapper;

    /**
     *  新增用户标签信息表数据
     * @param uid
     * @param labelIds
     * @return
     */
    @Override
    public void insertUserLabel(Long uid,String labelIds) {
        // 得到labelId集合
        List<Long> labelIdList = Arrays.asList(labelIds.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());

        // 先删除后新增
        userLabelInfoMapper.deleteByUid(uid);
        for (Long labelId : labelIdList){
            UserLabelInfo userLabelInfo = new UserLabelInfo(uid,labelId);
            // 循环新增数据
            userLabelInfoMapper.insertUserLabel(userLabelInfo);
        }
    }

    /**
     *  根据uid查询标签名集合
     * @param uid
     * @return
     */
    @Override
    public List<String> listLabelName(Long uid) {

        return userLabelInfoMapper.listLabelName(uid);
    }


}
