/**
 * FileName: CartService
 * Date:     2022/6/16 15:51
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.order.dto.fo.CartItemFO;
import com.woniu.order.outlet.dao.mysql.mapper.CartItemMapper;
import com.woniu.order.outlet.dao.mysql.po.CartItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/16 15:51
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class CartService {

    private final CartItemMapper cartItemMapper;
    private final SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(1L,11L);


    /**
     * <p>
     * 添加购物车
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/17 10:30
     * @param cartItemFO
     * @return boolean
     */
    public boolean addCart(CartItemFO cartItemFO){
        // 先查购物车
        CartItem product = findProduct(cartItemFO.getMemberId(), cartItemFO.getProductSkuId());
        // 购物车没有则新增
        if (product == null){
            return cartItemMapper.insert(CartItem.builder()
                    .id(snowFlakeGenerator.nextId())
                    .productId(cartItemFO.getProductId())
                    .productSkuId(cartItemFO.getProductSkuId())
                    .memberId(cartItemFO.getMemberId())
                    .quantity(cartItemFO.getQuantity())
                    .price(cartItemFO.getPrice())
                    .createDate(LocalDateTime.now())
                    .build()) > 0;
        }
        // 有则修改数量
        product.setQuantity(cartItemFO.getQuantity());
        return cartItemMapper.updateById(product) > 0;
    }

    /**
     * <p>
     * 查找该用户购物车的单个商品
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/17 11:04
     * @param memberId
     * @param productSkuId
     * @return com.woniu.order.outlet.dao.mysql.po.CartItem
     */
    private CartItem findProduct(Long memberId, Long productSkuId){
        return cartItemMapper.selectOne(Wrappers.<CartItem>lambdaQuery()
                .eq(CartItem::getMemberId, memberId)
                .eq(CartItem::getProductSkuId, productSkuId));
    }

    /**
     * <p>
     * 更新购物车数量
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/17 10:29
     * @param memberId
     * @param productSkuId
     * @param fixedQuantity
     * @return boolean
     */
    public boolean updateQuantity(Long memberId, Long productSkuId, Integer fixedQuantity){
        return cartItemMapper.update(null, Wrappers.<CartItem>lambdaUpdate()
                .set(CartItem::getQuantity, fixedQuantity)
                .eq(CartItem::getMemberId, memberId)
                .eq(CartItem::getProductSkuId, productSkuId)) > 0;
    }

    /**
     * <p>
     * 删除单件商品
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/17 11:29
     * @param memberId
     * @return boolean
     */
    public boolean deleteCartUnit(Long memberId, Long skuId){
        return cartItemMapper.delete(Wrappers.<CartItem>lambdaQuery()
                .eq(CartItem::getMemberId, memberId)
                .eq(CartItem::getProductSkuId, skuId)) > 0 ;
    }

    /**
     * <p>
     * 清空购物车
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/17 10:29
     * @param memberId
     * @return boolean
     */
    public boolean emptyCart(Long memberId){
        return cartItemMapper.delete(Wrappers.<CartItem>lambdaQuery()
                .eq(CartItem::getMemberId, memberId)) > 0 ;
    }
}