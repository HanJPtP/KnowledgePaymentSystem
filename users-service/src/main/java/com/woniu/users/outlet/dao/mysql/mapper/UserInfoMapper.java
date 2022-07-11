package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.users.inlet.web.dto.UserMsg;
import com.woniu.users.outlet.dao.mysql.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
* @author Han
* @description 针对表【user_info】的数据库操作Mapper
* @createDate 2022-06-11 10:51:22
* @Entity com.woniu.users.outlet.dao.mysql.po.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     *  根据uid 查询
     * @param uid
     * @return
     */
     default UserInfo getUserByUid(Long uid){
         return this.selectById(uid);
     }

    IPage<UserMsg> listUserInfo(IPage<UserMsg> page, @Param("levelName") String levelName,
                                @Param("startRegisterTime") String startRegisterTime,
                                @Param("endRegisterTime") String endRegisterTime,
                                @Param("labelName") String[] labelName,
                                @Param("uid") Long uid,
                                @Param("username") String username,
                                @Param("phone") String phone);

    // 根据uid集合查询email集合
    List<String> getEmailByUid(@Param("uidList") List<Long> uidList);

    /**
     *  新增用户信息
     * @param userInfo
     * @return
     */
    default int addUserInfo(UserInfo userInfo){
        return this.insert(userInfo);
    }

    /**
     *  根据唯一邮箱查询
     * @param email
     * @return
     */
    default List<UserInfo> selectByEmail(String email){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return this.selectList(queryWrapper);
    }

    /**
     *  得到所有的uid
     * @return
     */
    @Select("select uid from user_info")
    List<Long> getAllUserId();
}




