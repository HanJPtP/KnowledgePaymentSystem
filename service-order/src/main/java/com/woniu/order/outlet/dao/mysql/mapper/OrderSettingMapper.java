package com.woniu.order.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.order.outlet.dao.mysql.po.OrderSetting;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yxq
* @description 针对表【tb_order_setting】的数据库操作Mapper
* @createDate 2022-06-16 15:02:20
* @Entity com.woniu.order.outlet.dao.mysql.po.OrderSetting
*/
@Mapper
public interface OrderSettingMapper extends BaseMapper<OrderSetting> {


}
