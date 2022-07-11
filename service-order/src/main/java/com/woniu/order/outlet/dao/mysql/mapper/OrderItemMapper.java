package com.woniu.order.outlet.dao.mysql.mapper;

import com.woniu.api.outlet.web.entity.ProductInfoByOrderCenter;
import com.woniu.order.outlet.dao.mysql.po.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author yxq
* @description 针对表【tb_order_item】的数据库操作Mapper
* @createDate 2022-06-20 19:43:19
* @Entity com.woniu.order.outlet.dao.mysql.po.OrderItem
*/
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    @Select("select product_sku_id skuId, product_quantity quantity from\n" +
            "`tb_order_item`\n" +
            "where `order_id`=#{orderId}")
    List<ProductInfoByOrderCenter> getProductInfo (Long orderId);
}




