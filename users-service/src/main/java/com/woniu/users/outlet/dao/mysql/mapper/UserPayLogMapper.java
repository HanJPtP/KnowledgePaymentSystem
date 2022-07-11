package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserPayLog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Han
* @description 针对表【user_pay_log】的数据库操作Mapper
* @createDate 2022-06-14 10:31:17
* @Entity com.woniu.users.outlet.dao.mysql.po.UserPayLog
*/

public interface UserPayLogMapper extends BaseMapper<UserPayLog> {

    // 根据uid 查询用户消费费用集合
    @Select("select amount from user_pay_log where uid=#{uid}")
    List<UserPayLog> getAmountList(Long uid);

    /**
     *  根据uid查询对象集合
     * @param uid
     * @return
     */
    default List<UserPayLog> listUserPayLog(Long uid){
        QueryWrapper<UserPayLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.selectList(queryWrapper);
    }

}




