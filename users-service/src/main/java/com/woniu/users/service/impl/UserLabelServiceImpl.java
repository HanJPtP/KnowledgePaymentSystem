package com.woniu.users.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.users.inlet.web.dto.LabelInfoDto;
import com.woniu.users.outlet.dao.mysql.mapper.UserLabelMapper;
import com.woniu.users.outlet.dao.mysql.po.UserLabel;
import com.woniu.users.service.IUserLabelService;
import com.woniu.users.util.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserLabelServiceImpl implements IUserLabelService {
    private final UserLabelMapper userLabelMapper;

    /**
     *  查询所有标签集合
     * @return
     */
    @Override
    public List<UserLabel> listUserLabel() {
        return userLabelMapper.listUserLabel();
    }

    /**
     *  用户标签多条件查询
     * @param pageNo
     * @param pageSize
     * @param labelType
     * @param labelName
     * @param labelRank
     * @return
     */
    @Override
    public PageInfo<LabelInfoDto> listLabelIngoDto(Long pageNo, Long pageSize, String labelType, String labelName, String labelRank) {
        Page page = new Page(pageNo,pageSize);
        IPage iPage = userLabelMapper.listLabelInfo(page, labelType, labelName, labelRank);
        PageInfo<LabelInfoDto> pageInfo = new PageInfo<>(iPage);
        return pageInfo;
    }

    /**
     *  修改标签
     * @param userLabel
     * @return
     */
    @Override
    public int updateUserLabel(UserLabel userLabel) {
        UserLabel userLabel1 = userLabelMapper.selectById(userLabel.getLabelId());
        if(userLabel1 == null){
            return 0;
        }
        return userLabelMapper.updateUserLabel(userLabel);
    }

    /**
     *  新增标签
     * @param userLabel
     * @return
     */
    @Override
    public int addUserLabel(UserLabel userLabel) {
        // 查询是否重复新增
        QueryWrapper<UserLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("label_name", userLabel.getLabelName());
        UserLabel label = userLabelMapper.selectOne(queryWrapper);
        if(label == null) {
            // 执行新增
            return userLabelMapper.addUserLabel(userLabel);
        }else{
            return 0;
        }
    }
}
