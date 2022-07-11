package com.woniu.users.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.users.outlet.dao.mysql.po.LevelupGift;

/**
* @author Han
* @description 针对表【levelup_gift】的数据库操作Mapper
* @createDate 2022-06-15 15:04:49
* @Entity com.woniu.users.outlet.dao.mysql.po.LevelupGift
*/
public interface LevelupGiftMapper extends BaseMapper<LevelupGift> {

    /**
     *  根据giftId查询对象
     * @param giftId
     * @return
     */
    default LevelupGift getByGiftId(Long giftId){
        QueryWrapper<LevelupGift> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("gift_id", giftId);
        return this.selectOne(queryWrapper);
    }

    /**
     *  新增升级礼包类
     * @param levelupGift
     * @return
     */
    default int addLevelUpGift(LevelupGift levelupGift){
        return this.insert(levelupGift);
    }

    /**
     *  修改升级礼包类
     * @param levelupGift
     * @return
     */
    default int updateLevelUpGift(LevelupGift levelupGift){
        return this.updateById(levelupGift);
    }


}




