package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.MemberPlusType;

import java.util.List;

/**
* @author Han
* @description 针对表【member_plus_type】的数据库操作Mapper
* @createDate 2022-06-11 15:05:34
* @Entity com.woniu.users.outlet.dao.mysql.po.MemberPlusType
*/
public interface MemberPlusTypeMapper extends BaseMapper<MemberPlusType> {

    /**
     *  根据id 查询对象
     * @param plusTypeid
     * @return
     */
    default MemberPlusType getByPlusTypeid(Long plusTypeid){
        QueryWrapper<MemberPlusType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plus_typeid", plusTypeid);
        return this.selectOne(queryWrapper);
    }

    /**
     *  查询所有付费会员类型信息
     * @return
     */
    default List<MemberPlusType> listMemberPlusType(){
        return this.selectList(null);
    }

    /**
     *  新增付费会员类型信息
     * @param memberPlusType
     * @return
     */
    default int addMemberPlusType(MemberPlusType memberPlusType){
        return this.insert(memberPlusType);
    }

    /**
     *  修改付费会员类型信息
     * @param memberPlusType
     * @return
     */
    default int updateMemberPlusType(MemberPlusType memberPlusType){
        return this.updateById(memberPlusType);
    }

}




