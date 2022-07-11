package com.woniu.outnet.dao.mysql;

import com.woniu.outnet.dao.pojo.ClassCurrencyWatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuqi
 * @since 2022-06-11
 */
public interface ClassCurrencyWatchMapper extends BaseMapper<ClassCurrencyWatch> {
    /**
     * 通过用户查询看了什么课看了多长时间
     * @param userid
     * @return
     */
    List<ClassCurrencyWatch> listClassLiveWatchByUserid(@Param("userid") Long userid);

    /**
     * 进入的时候新增
     * @param classCurrencyWatch
     * @return
     */
    default int insertClassCurrencyWatch(ClassCurrencyWatch classCurrencyWatch){
        return this.insert(classCurrencyWatch);
    }

    /**
     * 修改
     * @param classCurrencyWatch
     * @return
     */
    default int updateClassCurrencyWatchById(ClassCurrencyWatch classCurrencyWatch){
        return this.updateById(classCurrencyWatch);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    default ClassCurrencyWatch getClassCurrencyWatchById(Long id){
        return this.selectById(id);
    }
}
