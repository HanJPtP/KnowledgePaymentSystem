package com.woniu.users.service;

import com.woniu.users.inlet.web.dto.UserPlusInfoDto;

public interface IUserPlusInfoService {

    /**
     *  根据uid查询UserPlusInfoDto对象
     * @param uid
     * @return
     */
    UserPlusInfoDto getByUid(Long uid);
}
