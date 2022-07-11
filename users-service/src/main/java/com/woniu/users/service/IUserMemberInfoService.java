package com.woniu.users.service;

import com.woniu.users.inlet.web.dto.UserMemberInfoDto;
import com.woniu.users.outlet.dao.mysql.po.UserCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMemberInfoService {
    /**
     *  根据用户id查询用户积分
     * @param uid
     * @return
     */
    List<Integer> getPoints(@Param("uid") Long uid);


    /**
     *  修改用户积分，改变用户积分，记录积分变动日志
     * @param uid
     * @param prepoints
     * @param nowpoints
     * @param note
     * @return
     */
    int updateUserMemberPoints(Long uid,
                               Integer prepoints,
                               Integer nowpoints,
                               String note);

    /**
     *  根据用户id查询成长值
     * @param uid
     * @return
     */
    List<Integer> getGrowthNum(@Param("uid") Long uid);

    /**
     *  修改用户成长值，改变用户成长值，记录成长值变动日志
     * @param uid
     * @param preGrowthNum
     * @param nowGrowthNum
     * @param note
     * @return
     */
    int updateUserMemberGrowthNum(Long uid,
                               Integer preGrowthNum,
                               Integer nowGrowthNum,
                               String note);

    /**
     *  根据uid查询UserMemberInfoDto对象
     * @param uid
     * @return
     */
    UserMemberInfoDto getUserMemberDtoByUid(Long uid);

    /**
     *  查询所有优惠券信息
     * @return
     */
    List<UserCoupon> selectAllCoupon();
}
