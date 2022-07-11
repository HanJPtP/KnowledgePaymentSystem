package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.users.outlet.dao.mysql.po.UserLearnMsg;

import java.util.Date;

/**
* @author Han
* @description 针对表【user_learn_msg】的数据库操作Mapper
* @createDate 2022-06-16 11:51:39
* @Entity com.woniu.users.outlet.dao.mysql.po.UserLearnMsg
*/
public interface UserLearnMsgMapper extends BaseMapper<UserLearnMsg> {

    /**
     *  根据uid查询对象
     * @param uid
     * @return
     */
    default UserLearnMsg getUserLearnMsgByUid(Long uid){
        QueryWrapper<UserLearnMsg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.selectOne(queryWrapper);
    }

    /**
     *  根据uid查询
     * @param uid
     * @return
     */
    default UserLearnMsg getByUid(Long uid){
        return this.selectById(uid);

    }

    /**
     *  新增
     * @param userLearnMsg
     * @return
     */
    default int addUserLearnMsg(UserLearnMsg userLearnMsg){
        // 查询新增的用户id是否重复
        UserLearnMsg byUid = getByUid(userLearnMsg.getUid());
        if(byUid == null) {
            userLearnMsg.setModifiedTime(new Date());
            return this.insert(userLearnMsg);
        } else {
            return 0;
        }

    }

}




