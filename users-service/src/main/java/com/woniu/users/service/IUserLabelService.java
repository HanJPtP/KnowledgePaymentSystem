package com.woniu.users.service;

import com.woniu.users.inlet.web.dto.LabelInfoDto;
import com.woniu.users.outlet.dao.mysql.po.UserLabel;
import com.woniu.users.util.PageInfo;

import java.util.List;

public interface IUserLabelService {
    /**
     *  查询所有标签集合
     * @return
     */
    List<UserLabel> listUserLabel();

    /**
     *  用户标签多条件查询
     * @param pageNo
     * @param pageSize
     * @param labelType
     * @param labelName
     * @param labelRank
     * @return
     */
    PageInfo<LabelInfoDto> listLabelIngoDto(Long pageNo,Long pageSize,
                                            String labelType,
                                            String labelName,
                                            String labelRank);

    /**
     *  修改标签
     * @param userLabel
     * @return
     */
    int updateUserLabel(UserLabel userLabel);

    /**
     *  新增标签
     */
    int addUserLabel(UserLabel userLabel);
}
