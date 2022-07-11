package com.woniu.order.outlet.dao.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.order.outlet.dao.mysql.po.CartItem;
import org.apache.ibatis.annotations.Mapper;


/**
* @author yxq
* @description 针对表【tb_cart_item】的数据库操作Mapper
* @createDate 2022-06-17 09:54:30
* @Entity com.woniu.order.outlet.dao.mysql.po.CartItem
*/
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {


}
