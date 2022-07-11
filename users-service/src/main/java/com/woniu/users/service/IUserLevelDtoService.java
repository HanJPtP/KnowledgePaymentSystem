package com.woniu.users.service;

import com.woniu.users.inlet.web.dto.UserLevelDto;
import com.woniu.users.outlet.dao.mysql.po.UserLevel;

public interface IUserLevelDtoService {

    /**
     *  新增免费会员等级信息
     * @param userLevelDto
     * @return
     */
    int addUserLevelDto(UserLevelDto userLevelDto);

    /**
     *  根据levelId查询会员等级信息
     * @param levelId
     * @return
     */
    UserLevelDto getUserLevelById(Long levelId);

    /**
     *  修改免费会员等级信息
     * @param userLevelDto
     * @return
     */
    int updateUserLevelDto(UserLevelDto userLevelDto);
}
