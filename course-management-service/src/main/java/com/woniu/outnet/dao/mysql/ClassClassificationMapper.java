package com.woniu.outnet.dao.mysql;

import com.woniu.outnet.dao.pojo.ClassClassification;
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
public interface ClassClassificationMapper extends BaseMapper<ClassClassification> {

    /**
     * 查询所有课程种类
     * @return
     */
    default List<ClassClassification> listAllClassClassIfication(){
        return this.selectList(null);
    }
}
