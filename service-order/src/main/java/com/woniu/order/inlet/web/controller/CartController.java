/**
 * FileName: CartController
 * Date:     2022/6/17 10:06
 * Author: YuanXQ
 * Description:
 */
package com.woniu.order.inlet.web.controller;

import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.order.dto.fo.CartItemFO;
import com.woniu.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author YuanXQ
 * @date 2022/6/17 10:06
 * @since 1.0.0
 */
@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    /**
     * <p>
     * 查询购物车
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/20 11:25
     * @param
     * @return com.woniu.knowledgepayment.commons.entity.ResponseResult<java.lang.Void>
     */
    // @GetMapping("/cart")
    // public ResponseResult<Void> listAllCart(@RequestParam("memberId") Long memberId){
    //
    //     return null;
    // }

    /**
     * <p>
     * 添加购物车
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/20 11:22
     * @param cartItemFO
     * @return com.woniu.knowledgepayment.commons.entity.ResponseResult<java.lang.Void>
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @PostMapping("/cart")
    public ResponseResult<Void> addCart(@RequestBody CartItemFO cartItemFO) {
        if (cartService.addCart(cartItemFO)) {
            return new ResponseResult<Void>(200,"购物车添加成功", null);
        } else {
            return new ResponseResult<Void>(5000,"购物车添加失败", null);
        }
    }

    /**
     * <p>
     * 删除单件购物车
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/20 11:23
     * @param memberId
     * @param productSkuId
     * @return com.woniu.knowledgepayment.commons.entity.ResponseResult<java.lang.Void>
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @DeleteMapping("/cart/unit")
    public ResponseResult<Void> deleteCartUnit(@RequestParam("memberId") Long memberId,
                                               @RequestParam("productSkuId") Long productSkuId) {
        if (cartService.deleteCartUnit(memberId, productSkuId)) {
            return new ResponseResult<Void>(200,"购物车删除成功", null);
        }else {
            return new ResponseResult<Void>(5000,"购物车删除失败", null);
        }
    }

    /**
     * <p>
     * 清空购物车
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/20 11:23
     * @param memberId
     * @return com.woniu.knowledgepayment.commons.entity.ResponseResult<java.lang.Void>
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @DeleteMapping("/cart/all")
    public ResponseResult<Void> deleteCartUnit(@RequestParam("memberId") Long memberId) {
        if (cartService.emptyCart(memberId)) {
            return new ResponseResult<Void>(200,"购物车删除成功", null);
        }else {
            return new ResponseResult<Void>(5000,"购物车删除失败", null);
        }
    }

    /**
     * <p>
     * 修改购物车数量
     * </p>
     *
     * @author YuanXQ
     * @date 2022/6/20 11:23
     * @param memberId
     * @param productSkuId
     * @param fixedQuantity
     * @return com.woniu.knowledgepayment.commons.entity.ResponseResult<java.lang.Void>
     */
    @PreAuthorize("hasAnyAuthority('order:admin')")
    @PutMapping("/cart")
    public ResponseResult<Void> updateCartQuantity(@RequestParam("memberId") Long memberId,
                                                   @RequestParam("productSkuId") Long productSkuId,
                                                   @RequestParam("fixedQuantity") Integer fixedQuantity) {
        if (cartService.updateQuantity(memberId, productSkuId, fixedQuantity)) {
            return new ResponseResult<Void>(200,"购物车修改成功", null);
        }else {
            return new ResponseResult<Void>(5000,"购物车修改失败", null);
        }
    }


}