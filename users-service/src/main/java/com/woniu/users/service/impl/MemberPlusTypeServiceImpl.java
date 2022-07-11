package com.woniu.users.service.impl;

import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.users.outlet.dao.mysql.mapper.MemberPlusTypeMapper;
import com.woniu.users.outlet.dao.mysql.po.MemberPlusType;
import com.woniu.users.service.IMemberPlusTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberPlusTypeServiceImpl implements IMemberPlusTypeService {

    private final MemberPlusTypeMapper memberPlusTypeMapper;

    /**
     *  查询所有付费会员类型信息
     * @return
     */
    @Override
    public List<MemberPlusType> listMemberPlusType() {
        return memberPlusTypeMapper.listMemberPlusType();
    }

    /**
     *  新增付费会员类型信息
     * @param memberPlusType
     * @return
     */
    public int addMemberPlusType(MemberPlusType memberPlusType){
        memberPlusType.setPlusTypeid(new SnowFlakeGenerator(1,5).nextId());
        memberPlusType.setStatus(1);
        memberPlusType.setModifiedTime(new Date());
        return memberPlusTypeMapper.addMemberPlusType(memberPlusType);
    }

    /**
     *  修改付费会员类型信息
     * @param memberPlusType
     * @return
     */
    public int updateMemberPlusType(MemberPlusType memberPlusType){
        MemberPlusType memberPlusType1 = memberPlusTypeMapper.selectById(memberPlusType.getPlusTypeid());
        if(memberPlusType1 == null){
            return 0;
        }
        memberPlusType.setModifiedTime(new Date());
        return memberPlusTypeMapper.updateMemberPlusType(memberPlusType);
    }

    /**
     *  根据id查询
     * @param plusTypeid
     * @return
     */
    @Override
    public MemberPlusType getByPlusTypeid(Long plusTypeid) {

        return memberPlusTypeMapper.getByPlusTypeid(plusTypeid);
    }
}
