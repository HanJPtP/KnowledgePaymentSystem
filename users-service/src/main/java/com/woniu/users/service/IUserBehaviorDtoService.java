package com.woniu.users.service;

import com.woniu.users.inlet.web.dto.UserBehaviorDto;

public interface IUserBehaviorDtoService {

    /**
     *  根据用户id得到UserBehaviorDto对象
     * @param uid
     * @return
     */
    UserBehaviorDto getUserBehaviorByUid(Long uid);
}
