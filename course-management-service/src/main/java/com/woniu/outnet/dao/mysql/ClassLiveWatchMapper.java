package com.woniu.outnet.dao.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outnet.dao.pojo.ClassLiveWatch;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
public interface ClassLiveWatchMapper extends BaseMapper<ClassLiveWatch> {
    /**
     * 查询是谁看了什么视频
     * @param userid
     * @return
     */
    List<ClassLiveWatch> listClassLiveWatchByUserid(@Param("userid") Long userid);

    /**
     * 进入直播间的时候
     * @param classLiveWatch
     * @return
     */
    default int insertClassLiveWatchWhileIn(ClassLiveWatch classLiveWatch){
        return this.insert(classLiveWatch);
    }

    /**
     * 修改信息
     * @param classLiveWatch
     * @return
     */
    default int updateClassLiveWatchById(ClassLiveWatch classLiveWatch){
        return this.updateById(classLiveWatch);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    default ClassLiveWatch getClassLiveWatchById(Long id){
        return this.selectById(id);
    }

    @Select("select count(*) from class_live_watch group by crlid having crlid = #{crlid}")
    Long getTotleByCrlid(@Param("crlid") Long crlid);

}
