package com.woniu.order.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.order.outlet.dao.mysql.po.OrderOperateHistory;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yxq
* @description 针对表【tb_order_operate_history】的数据库操作Mapper
* @createDate 2022-06-16 15:01:48
* @Entity com.woniu.order.outlet.dao.mysql.po.OrderOperateHistory
*/
@Mapper
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistory> {


}
