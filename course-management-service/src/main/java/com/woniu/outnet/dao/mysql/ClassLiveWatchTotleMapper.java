package com.woniu.outnet.dao.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outnet.dao.pojo.ClassLiveWatchTotle;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
public interface ClassLiveWatchTotleMapper extends BaseMapper<ClassLiveWatchTotle> {

    default ClassLiveWatchTotle getClassLiveWatchByCrlid(Long crlid){
        QueryWrapper<ClassLiveWatchTotle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("crlid", crlid);
        return this.selectOne(queryWrapper);
    }

}
