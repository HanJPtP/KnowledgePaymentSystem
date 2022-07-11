/**
 * FileName: OrderService
 * Date:     2022/6/16 10:04
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.api.outlet.web.entity.PointsInfoToUserCenter;
import com.woniu.api.outlet.web.entity.ProductInfoByOrderCenter;
import com.woniu.dto.CouponInfoToEventCenter;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.order.dto.fo.FlashFO;
import com.woniu.order.dto.fo.OrderDetailFO;
import com.woniu.order.dto.fo.OrderFO;
import com.woniu.order.outlet.client.MessageClient;
import com.woniu.order.outlet.dao.mysql.mapper.OrderItemMapper;
import com.woniu.order.outlet.dao.mysql.mapper.OrderMapper;
import com.woniu.order.outlet.dao.mysql.po.Order;
import com.woniu.order.outlet.dao.mysql.po.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/16 10:04
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final MessageClient messageClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(3L,12L);

    /**
     * <p>
     * 秒杀订单存入消息队列
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/22 18:33
     * @param flashFO
     * @return boolean
     */
    public boolean sendFlashMessage(FlashFO flashFO){
        try {
            String string = objectMapper.writeValueAsString(flashFO);
            messageClient.saveMessage(
                    MessageDTO.builder()
                            .exchange("order_center.direct")
                            .routingKey("FlashOrder")
                            .content(string)
                            .contactMail("test@gmail.com")
                            .build());
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * <p>
     * 秒杀订单存入数据库
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/22 18:33
     * @param flashFO
     * @return java.lang.Long
     */
    public void addFlashOrder(FlashFO flashFO) {
        long orderId = snowFlakeGenerator.nextId();

        orderItemMapper.insert(OrderItem.builder()
                .id(snowFlakeGenerator.nextId())
                .orderId(orderId)
                .productQuantity(1)
                .productSkuId(flashFO.getProductSkuId())
                .productPrice(flashFO.getPrice())
                .productPic(flashFO.getProductPic())
                .productName(flashFO.getProductName())
                .build());
        orderMapper.insert(Order.builder()
                .id(orderId)
                .memberId(flashFO.getMemberId())
                .orderType(1)
                .status(0)
                .acatualPayAmount(flashFO.getActualPayAmount())
                .createTime(LocalDateTime.now())
                .build());
    }



    /**
     * 添加普通订单
     * @param orderFO
     * @return
     */
    public Long addOrder (OrderFO orderFO){
        long orderId = snowFlakeGenerator.nextId();
        List<OrderDetailFO> orderDetailFOList = orderFO.getOrderDetailFOList();

        orderDetailFOList.forEach(orderDetailFO -> orderItemMapper.insert(OrderItem.builder()
                .id(snowFlakeGenerator.nextId())
                .orderId(orderId)
                .productSkuId(orderDetailFO.getProductSkuId())
                .productName(orderDetailFO.getProductName())
                .productPic(orderDetailFO.getProductPic())
                .productPrice(orderDetailFO.getProductPrice())
                .productQuantity(orderDetailFO.getProductQuantity())
                .build()));

        int insert = orderMapper.insert(Order.builder()
                .id(orderId)
                .memberId(orderFO.getMemberId())
                .memberUsername(orderFO.getMemberUsername())
                .orderSn("SN-" + orderFO.getMemberUsername() + "-" + LocalDateTime.now())
                .couponId(orderFO.getCouponId())
                .createTime(LocalDateTime.now())
                .totalAmount(orderFO.getTotalAmount())
                .freightAmount(orderFO.getFreightAmount())
                .promotionAmount(orderFO.getPromotionAmount())
                .integrationAmount(orderFO.getIntegrationAmount())
                .couponAmount(orderFO.getCouponAmount())
                .acatualPayAmount(orderFO.getAcatualPayAmount())
                .sourceType(orderFO.getSourceType())
                .status(0)
                .payType(orderFO.getPayType())
                .orderType(orderFO.getOrderType())
                .deliveryCompany(orderFO.getDeliveryCompany())
                .receiverName(orderFO.getReceiverName())
                .receiverPhone(orderFO.getReceiverPhone())
                .receiverPostCode(orderFO.getReceiverPostCode())
                .receiverProvince(orderFO.getReceiverProvince())
                .receiverCity(orderFO.getReceiverCity())
                .receiverRegion(orderFO.getReceiverRegion())
                .receiverDetailAddress(orderFO.getReceiverDetailAddress())
                .note(orderFO.getNote())
                .useIntegration(orderFO.getUseIntegration())
                .build());
        if (insert > 0 ){
            return orderId;
        } else {
            return 0L;
        }


        // if (1 == orderFO.getOrderType()) {
        //     //TODO 调用支付宝接口
        //
        // } else if (2 == orderFO.getOrderType()) {
        //     //TODO 调用微信接口
        //
        // }
    }

    public boolean payTheBill(Long orderId) {
        Order order = orderMapper.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getId, orderId).eq(Order::getStatus, 0));

        if (order == null) {
            return false;
        }

        List<ProductInfoByOrderCenter> productInfo = orderItemMapper.getProductInfo(orderId);

        order.setStatus(1);
        order.setPaymentTime(LocalDateTime.now());

        CouponInfoToEventCenter couponInfoToEventCenter = CouponInfoToEventCenter.builder()
                .couponId(order.getCouponId())
                .memberId(order.getMemberId())
                .totalAmount(order.getTotalAmount())
                .build();
        PointsInfoToUserCenter pointsInfoToUserCenter = PointsInfoToUserCenter.builder().orderId(order.getId())
                .memberId(order.getMemberId())
                .totalAmount(Double.valueOf(String.valueOf(order.getTotalAmount())))
                .productList(productInfo)
                .build();


        try {
            String jsonStr = objectMapper.writeValueAsString(couponInfoToEventCenter);
            MessageDTO toEventCenter = MessageDTO.builder().exchange("order_center.direct")
                    .routingKey("ToEventCenter")
                    .contactMail("872160139@qq.com")
                    .content(jsonStr)
                    .build();
            messageClient.saveMessage(toEventCenter);

            String jsonStr2 = objectMapper.writeValueAsString(pointsInfoToUserCenter);
            MessageDTO toUserCenter = MessageDTO.builder().exchange("order_center.direct")
                    .routingKey("ToUserCenter")
                    .contactMail("872160139@qq.com")
                    .content(jsonStr2)
                    .build();
            messageClient.saveMessage(toUserCenter);

            orderMapper.updateById(order);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
