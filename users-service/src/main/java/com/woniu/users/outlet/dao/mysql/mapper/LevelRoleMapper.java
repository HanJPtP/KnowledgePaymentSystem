package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.LevelRole;

/**
* @author Han
* @description 针对表【level_role】的数据库操作Mapper
* @createDate 2022-06-16 19:50:32
* @Entity com.woniu.users.outlet.dao.mysql.po.LevelRole
*/
public interface LevelRoleMapper extends BaseMapper<LevelRole> {

    /**
     *  新增等级规则
     * @param levelRole
     * @return
     */
    default int addLevelRole(LevelRole levelRole){
        return this.insert(levelRole);
    }

    /**
     *  修改等级规则
     * @param levelRole
     * @return
     */
    default int updateLevelRole(LevelRole levelRole){
        return this.updateById(levelRole);
    }

    /**
     *  根据id查询对象
     * @param roleid
     * @return
     */
    default LevelRole getLevelRoleById(Long roleid){
        return this.selectById(roleid);
    }
}




