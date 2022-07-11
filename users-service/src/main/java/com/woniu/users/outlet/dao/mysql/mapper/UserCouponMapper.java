package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserCoupon;

import java.util.List;

/**
* @author Han
* @description 针对表【user_coupon】的数据库操作Mapper
* @createDate 2022-06-15 15:21:52
* @Entity com.woniu.users.outlet.dao.mysql.po.UserCoupon
*/
public interface UserCouponMapper extends BaseMapper<UserCoupon> {

    /**
     *  新增对象
     * @param userCoupon
     * @return
     */
    default int addUserCoupon(UserCoupon userCoupon){
        return this.insert(userCoupon);
    }

    /**
     *  查询所有
     * @return
     */
    default List<UserCoupon> selectAll(){
        return this.selectList(null);
    }

}




