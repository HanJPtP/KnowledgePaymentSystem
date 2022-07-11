package com.woniu.users.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.users.inlet.web.dto.UserDetailDto;
import com.woniu.users.inlet.web.dto.UserMsg;
import com.woniu.users.outlet.dao.mysql.po.UserInfo;
import com.woniu.users.util.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IUserInfoService {

    UserInfo getUserByUid(Long uid);

     /**
     *    用户多条件查询
     * @param pageNo
     * @param pageSize
     * @param levelName
     * @param
     * @param labelName
     * @param uid
     * @param username
     * @param phone
     * @return
     */
    PageInfo<UserMsg> listUserInfo(Long pageNo, Long pageSize, String levelName,
                                   String startRegisterTime,
                                   String endRegisterTime,
                                   String[] labelName,
                                   Long uid,
                                   String username,
                                   String phone);


    /**
     *  根据uid集合查询email集合
     * @param uidList
     * @return
     */
    List<String> getEmailByUid(List<Long> uidList);

    /**
     *  根据用户uid查询详细信息
     * @param uid
     * @return
     */
    UserDetailDto getUserDetailByUid(Long uid);

    /**
     *  新增用户信息
     * @param userInfo
     * @return
     */
    int addUserInfo(UserInfo userInfo);
}
