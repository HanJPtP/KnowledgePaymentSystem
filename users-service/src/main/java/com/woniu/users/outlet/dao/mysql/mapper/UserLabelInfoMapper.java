package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.UserLabelInfo;

import java.util.List;

/**
* @author Han
* @description 针对表【user_label_info】的数据库操作Mapper
* @createDate 2022-06-14 15:14:35
* @Entity com.woniu.users.outlet.dao.mysql.po.UserLabelInfo
*/
public interface UserLabelInfoMapper extends BaseMapper<UserLabelInfo> {

    /**
     *  新增用户标签信息表数据
     * @param userLabelInfo
     * @return
     */
    default int insertUserLabel(UserLabelInfo userLabelInfo){
        return this.insert(userLabelInfo);
    }

    /**
     *  删除
     * @param uid
     * @return
     */
    default int deleteByUid(Long uid){
        QueryWrapper<UserLabelInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.delete(queryWrapper);
    }

    /**
     *  根据uid查询标签名集合
     * @param uid
     * @return
     */
    List<String> listLabelName(Long uid);

}




