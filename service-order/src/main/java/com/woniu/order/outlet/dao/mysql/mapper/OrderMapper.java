package com.woniu.order.outlet.dao.mysql.mapper;

import com.woniu.order.outlet.dao.mysql.po.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yxq
* @description 针对表【tb_order】的数据库操作Mapper
* @createDate 2022-06-20 16:31:22
* @Entity com.woniu.order.outlet.dao.mysql.po.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




