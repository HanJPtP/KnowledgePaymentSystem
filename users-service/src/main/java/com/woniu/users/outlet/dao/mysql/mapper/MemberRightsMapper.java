package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.users.outlet.dao.mysql.po.MemberRights;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Han
* @description 针对表【member_rights】的数据库操作Mapper
* @createDate 2022-06-11 15:07:22
* @Entity com.woniu.users.outlet.dao.mysql.po.MemberRights
*/
public interface MemberRightsMapper extends BaseMapper<MemberRights> {

    /**
     *  根据id集合查询启用状态的对象集合
     * @param rightsIds
     * @return
     */
    default List<MemberRights> listMemberRightsById(List<Long> rightsIds){
        QueryWrapper<MemberRights> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("right_id",rightsIds).eq("status", 1);
        return this.selectList(queryWrapper);
    }

    /**
     *  多条件查询
     * @param page
     * @param rightName
     * @return
     */
    IPage<MemberRights> listMemberRights(IPage<MemberRights> page,@Param("rightName") String rightName);

    /**
     *  新增
     * @param memberRights
     * @return
     */
    default int addMemberRights(MemberRights memberRights){
        return this.insert(memberRights);
    }

    /**
     *  修改
     * @param memberRights
     * @return
     */
    default int updateMemberRights(MemberRights memberRights){
        return this.updateById(memberRights);
    }

    /**
     *  根据rightName查询
     * @param rightName
     * @return
     */
    default List<MemberRights> selectByName(String rightName){
        QueryWrapper<MemberRights> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("right_name", rightName);
        return this.selectList(queryWrapper);

    }

    /**
     *  根据id查询对象
     */
    default MemberRights selectByRightId(Long rightId){
        return this.selectById(rightId);
    }
}




