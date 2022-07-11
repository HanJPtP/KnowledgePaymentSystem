package com.woniu.outnet.dao.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outnet.dao.pojo.ClassAppointment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
public interface ClassAppointmentMapper extends BaseMapper<ClassAppointment> {
    /**
     * 查询所有未发送信息的人
     * @param sendOrNot
     * @return
     */
    default List<ClassAppointment> listAllClassAppointmentBySendOrNotIsN(String sendOrNot){
        QueryWrapper<ClassAppointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sendornot", sendOrNot);
        return this.selectList(queryWrapper);
    }

    /**
     * 查找是在逻辑上同一时间段只能对用一个直播间关注一次,不能生成多条数据
     * @param userid
     * @param crlid
     * @param date
     * @return
     */
    default int getClassAppointmentIsExists(Long userid, Long crlid, Date date){
        QueryWrapper<ClassAppointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid).eq("crlid", crlid).eq("estarttime", date);
        List<ClassAppointment> classAppointments = this.selectList(queryWrapper);
        int i =0;
        if(classAppointments.size()>0){
            i = 1;
        }
        return i;
    }

    /**
     * 查到唯一数据
     * @param userid
     * @param crlid
     * @param date
     * @return
     */
    default ClassAppointment getClassAppointmentByUseridAndCrlidAndEstartTime(Long userid, Long crlid, Date date){
        QueryWrapper<ClassAppointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid).eq("crlid", crlid).eq("estarttime", date);
        return this.selectOne(queryWrapper);
    }
    /**
     * 查询所有时间符合要求,状态为规定状态的
     * @param sendOrNot
     * @param leftTime
     * @param rightTime
     * @return
     */
    @ResultMap("ca_crl")
    @Select("select * from class_appointment where sendornot=#{sendOrNot} and estarttime <= #{rightTime} and  estarttime >= #{leftTime}")
    List<ClassAppointment> listClassAppointmentBySendOrNotAndTime(@Param("sendOrNot") String sendOrNot, @Param("leftTime") String leftTime, @Param("rightTime") String rightTime);
    /**
     * 查询所有关注了这个课的信息
     * @param crlid
     * @return
     */
    default List<ClassAppointment> listAllClassAppointmentByCrlid(Long crlid){
        QueryWrapper<ClassAppointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("crlid", crlid);
        return this.selectList(queryWrapper);
    }
    /**
     * 添加
     * @param classAppointment
     * @return
     */
    default int insertClassAppointment(ClassAppointment classAppointment){
        return this.insert(classAppointment);
    }

    /**
     * 根据id修改
     * @param classAppointment
     * @return
     */
    default int updateClassAppointmentById(ClassAppointment classAppointment){
        return this.updateById(classAppointment);
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    default int deleteClassAppointmentById(Long id){
        return this.deleteById(id);
    }

    /**
     * 根据集合修改发送的对象的状态
     * @param ids
     * @return
     */
    int updateClassAppointmentByListIdToChangeSendOrNot(@Param("ids") List<Long> ids);

    /**
     * 根据用户id更改是否发放消息的状态
     * @param userid
     * @param userStatus
     * @return
     */
    default int updateClassAppointmentUserStatusByUserid(Long userid,String userStatus){
        ClassAppointment classAppointment = new ClassAppointment();
        classAppointment.setUserStatus(userStatus);
        UpdateWrapper<ClassAppointment> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid", userid);
        return this.update(classAppointment,updateWrapper);
    }

}
