package com.woniu.outnet.dao.mysql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuqi
 * @since 2022-06-10
 */
public interface ClassCurrencyMsgMapper extends BaseMapper<ClassCurrencyMsg> {


    default Page<ClassCurrencyMsg> listClassCurrencyMsgByChooseAndPage(Page<ClassCurrencyMsg> page, QueryWrapper<ClassCurrencyMsg> queryWrapper) {
        return this.selectPage(page, queryWrapper);
    }

    default ClassCurrencyMsg listClassCurrencyMsgById(Long id) {
        return this.selectById(id);
    }

    //定时上架所需要获取的对象
    default List<ClassCurrencyMsg> listClassCurrencyMsgByStartTime(Date startTime) {
        QueryWrapper<ClassCurrencyMsg> queryWrapper = new QueryWrapper<>();
        System.out.println(new Date(startTime.getTime() + 3600000l));
        queryWrapper.lt("starttime", new Date(startTime.getTime() + 3600000l)).gt("starttime", new Date(startTime.getTime())).eq("status", 2);
        return this.selectList(queryWrapper);
    }

    //定时下架,状态为下架的
    default List<ClassCurrencyMsg> listClassCurrencyMsgByEndTime(Date endTime) {
        QueryWrapper<ClassCurrencyMsg> queryWrapper = new QueryWrapper<>();
        System.out.println(new Date(endTime.getTime() + 3600000l));
        queryWrapper.lt("endtime", new Date(endTime.getTime() + 3600000l)).gt("endtime", new Date(endTime.getTime())).eq("status", 1);
        return this.selectList(queryWrapper);
    }
}
