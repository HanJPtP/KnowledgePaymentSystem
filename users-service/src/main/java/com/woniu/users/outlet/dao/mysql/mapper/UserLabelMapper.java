package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniu.users.inlet.web.dto.LabelInfoDto;
import com.woniu.users.outlet.dao.mysql.po.UserLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Han
* @description 针对表【user_label】的数据库操作Mapper
* @createDate 2022-06-14 16:22:58
* @Entity com.woniu.users.outlet.dao.mysql.po.UserLabel
*/
public interface UserLabelMapper extends BaseMapper<UserLabel> {

    /**
     *  查询所有标签对象
     * @return
     */
    default List<UserLabel> listUserLabel(){
        // 查使用状态的标签
        QueryWrapper<UserLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return this.selectList(queryWrapper);
    }

    IPage<LabelInfoDto> listLabelInfo(IPage<LabelInfoDto> page,
                                      @Param("labelType") String labelType,
                                      @Param("labelName") String labelName,
                                      @Param("labelRank") String labelRank);

    /**
     *  修改标签
     * @param userLabel
     * @return
     */
    default int updateUserLabel(UserLabel userLabel){
        return this.updateById(userLabel);
    }

    /**
     *  新增标签
     */
    default int addUserLabel(UserLabel userLabel){
        return this.insert(userLabel);
    }

}




