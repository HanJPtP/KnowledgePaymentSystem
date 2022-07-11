package com.woniu.users.service;

import com.woniu.users.outlet.dao.mysql.po.MemberRights;
import com.woniu.users.util.PageInfo;

public interface IMemberRightsService {

    /**
     *  会员权益条件查询
     * @param pageNo
     * @param pageSize
     * @param rightName
     * @return
     */
    PageInfo<MemberRights> listMemberRights(Long pageNo,Long pageSize,String rightName);


    /**
     *  新增
     * @param memberRights
     * @return
     */
    int addMemberRights(MemberRights memberRights);

    /**
     *  修改
     * @param memberRights
     * @return
     */
    int updateMemberRights(MemberRights memberRights);

    /**
     *  根据id查询对象
     * @param rightId
     * @return
     */
    MemberRights selectByRightId(Long rightId);
}
