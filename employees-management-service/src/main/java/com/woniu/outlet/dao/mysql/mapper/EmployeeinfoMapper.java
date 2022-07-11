package com.woniu.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.Employeeinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
* @author ThinkPad
* @description 针对表【employeeinfo】的数据库操作Mapper
* @createDate 2022-06-19 21:51:40
* @Entity com.woniu.outlet.dao.mysql.po.Employeeinfo
*/
public interface EmployeeinfoMapper extends BaseMapper<Employeeinfo> {

    @Update("update employeeinfo set is_deleted = 'y' where uid = #{uid} and is_deleted not in ('y')")
    int removeEmployeeInfo(@Param("uid") Long uid);

    @Update("update employeeinfo set status = #{fo.status} where id = #{fo.id} and status not in (#{fo.status})")
    int updateEmployeeInfoItemStatus(@Param("fo") Employeeinfo fo);

}




