package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.users.outlet.dao.mysql.po.UserCourseMsg;

import java.util.Date;

/**
* @author Han
* @description 针对表【user_course_msg】的数据库操作Mapper
* @createDate 2022-06-18 11:12:50
* @Entity com.woniu.users.outlet.dao.mysql.po.UserCourseMsg
*/
public interface UserCourseMsgMapper extends BaseMapper<UserCourseMsg> {

    /**
     *  根据uid和课程id查询
     * @param uid
     * @param courseId
     * @return
     */
    default UserCourseMsg getUserCourseMsg(Long uid, Long courseId){
        QueryWrapper<UserCourseMsg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("course_id", courseId);
        return this.selectOne(queryWrapper);
    }

    /**
     *  新增
     * @param userCourseMsg
     * @return
     */
    default int addUserCourseMsg(UserCourseMsg userCourseMsg){
        // 判断是否重复新增
        UserCourseMsg userCourseMsg1 = getUserCourseMsg(userCourseMsg.getUid(), userCourseMsg.getCourseId());
        if (userCourseMsg1 == null) {
            userCourseMsg.setModifiedTime(new Date());
            return this.insert(userCourseMsg);
        } else {
            return 0;
        }

    }



}




