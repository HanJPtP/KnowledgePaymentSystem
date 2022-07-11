package com.woniu.outnet.dao.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outnet.dao.pojo.ClassRecordLive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
public interface ClassRecordLiveMapper extends BaseMapper<ClassRecordLive> {
    /**
     * 多条件分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    default Page<ClassRecordLive> listAllClassLiveWatchByChooseAndPage(Page<ClassRecordLive> page, QueryWrapper<ClassRecordLive> queryWrapper){
        return  this.selectPage(page, queryWrapper);
    }

    /**
     * 查单个直播
     * @param id
     * @return
     */
    default ClassRecordLive listClassLiveWatchById(Long id){
        return this.selectById(id);
    }
}
