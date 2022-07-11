package com.woniu.users.inlet.web.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.woniu.users.outlet.dao.mysql.po.MemberRights;
import com.woniu.users.outlet.dao.mysql.po.UserLevel;
import com.woniu.users.outlet.dao.mysql.po.UserMemberInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  用户会员等级信息类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMemberInfoDto implements Serializable {

    /**
     *  用户会员信息
     */
    private UserMemberInfo userMemberInfo;

    /**
     *  用户会员等级信息
     */
    private UserLevel userLevel;

    /**
     *  有效权益对象集合
     */
    private List<MemberRights> memberRightsList;


}
