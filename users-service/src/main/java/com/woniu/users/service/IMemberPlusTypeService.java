package com.woniu.users.service;

import com.woniu.users.outlet.dao.mysql.po.MemberPlusType;

import java.util.List;

public interface IMemberPlusTypeService {
    /**
     *  查询所有付费会员类型信息
     * @return
     */
    List<MemberPlusType> listMemberPlusType();

    /**
     *  新增付费会员类型信息
     * @param memberPlusType
     * @return
     */
    int addMemberPlusType(MemberPlusType memberPlusType);

    /**
     *  修改付费会员类型信息
     * @param memberPlusType
     * @return
     */
    int updateMemberPlusType(MemberPlusType memberPlusType);

    /**
     *  根据id查询
     * @param plusTypeid
     * @return
     */
    MemberPlusType getByPlusTypeid(Long plusTypeid);
}
