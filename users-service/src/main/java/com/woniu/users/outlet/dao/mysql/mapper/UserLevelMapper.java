package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.users.inlet.web.dto.UserLevelDto;
import com.woniu.users.outlet.dao.mysql.po.UserLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Han
* @description 针对表【user_level】的数据库操作Mapper
* @createDate 2022-06-11 15:00:23
* @Entity com.woniu.users.outlet.dao.mysql.po.UserLevel
*/
@Mapper
public interface UserLevelMapper extends BaseMapper<UserLevel> {

    /**
     *  根据成长值，判断等级，得到等级对象
     * @param growthNum
     * @return
     */
    List<UserLevel> getUserLevelByGrowthNum(@Param("growthNum") Integer growthNum);

    /**
     *  根据id 查询对象
     * @param levelId
     * @return
     */
    default UserLevel getByLevelId(Long levelId){
        QueryWrapper<UserLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level_id", levelId);
        return this.selectOne(queryWrapper);
    }

    /**
     *  条件查询免费会员等级信息
     * @param page
     * @param levelName
     * @return
     */
    IPage<UserLevel> listUserLevel(IPage<UserLevel> page,@Param("levelName") String levelName);

    /**
     *  新增用户等级
     * @param userLevel
     * @return
     */
    default int addUserLevel(UserLevel userLevel){
        return this.insert(userLevel);
    }

    /**
     *  根据等级数或等级名查询对象
     * @param levelNumber
     * @param levelName
     * @return
     */
    @Select("select * from user_level where level_number=#{levelNumber} or level_name=#{levelName}")
    List<UserLevel> selectUserLevel(@Param("levelNumber") String levelNumber,@Param("levelName") String levelName);

    /**
     *  修改用户等级
     * @param userLevel
     * @return
     */
    default int updateUserLevel(UserLevel userLevel){
        return this.updateById(userLevel);
    }
}




