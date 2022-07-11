package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserMemberInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
* @author Han
* @description 针对表【user_member_info】的数据库操作Mapper
* @createDate 2022-06-11 15:03:34
* @Entity com.woniu.users.outlet.dao.mysql.po.UserMemberInfo
*/
public interface UserMemberInfoMapper extends BaseMapper<UserMemberInfo> {

    /**
     *  根据用户id查询用户积分
     * @param uid
     * @return
     */
    @Select("select points from user_member_info where uid=#{uid}")
    List<Integer> getPoints(@Param("uid") Long uid);

    /**
     *  修改用户会员积分
     * @param points
     * @param uid
     * @return
     */
    @Update("update user_member_info set points=#{points},modified_time=#{newDate} where uid=#{uid}")
    int updateUserMemberPoints(@Param("points") Integer points,@Param("uid") Long uid, @Param("newDate") Date newDate);

    /**
     *  根据用户id查询用户成长值
     * @param uid
     * @return
     */
    @Select("select growth_num from user_member_info where uid=#{uid}")
    List<Integer> getGrowthNum(@Param("uid") Long uid);

    /**
     *  修改用户会员成长值
     * @param growthNum
     * @param uid
     * @param newDate
     * @return
     */
    @Update("update user_member_info set growth_num=#{growthNum},modified_time=#{newDate} where uid=#{uid}")
    int updateUserMemberGrowthNum(@Param("growthNum") Integer growthNum, @Param("uid") Long uid, @Param("newDate") Date newDate);

    /**
     *  根据会员id查询对象
     * @param uid
     * @return
     */
    default UserMemberInfo getByUid(Long uid){
        QueryWrapper<UserMemberInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.selectOne(queryWrapper);
    }

    /**
     *  修改用户信息
     * @param userMemberInfo
     * @return
     */
    default int updateUserMemberInfo(UserMemberInfo userMemberInfo){
        return this.updateById(userMemberInfo);
    }


    /**
     *  新增
     * @param userMemberInfo
     * @return
     */
    default int addUserMemberInfo(UserMemberInfo userMemberInfo){
        return this.insert(userMemberInfo);
    }



}




