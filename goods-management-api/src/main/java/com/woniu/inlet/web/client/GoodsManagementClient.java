package com.woniu.inlet.web.client;

import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.outlet.dao.dto.OrderGoodsSkuItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public interface GoodsManagementClient {

    /**
     * 分页多条件查询商品列表
     * @param saleable
     * @param title
     * @param startSaleTime
     * @param endSaleTime
     * @param pageno
     * @param pageSize
     * @return
     */
    @GetMapping("/goods/list")
    ResponseResult listGoodsItem(@RequestParam("saleable") String saleable,
                                 @RequestParam("title") String title,
                                 @RequestParam("startTime")Date startSaleTime,
                                 @RequestParam("endTime")Date endSaleTime,
                                 @RequestParam(value = "pageno", required = true, defaultValue = "1") Integer pageno,
                                 @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize);

    /**
     * 添加商品
     * @return
     */
    @PostMapping("/goods/add")
    ResponseResult saveGoodsItem();

    /**
     * 更新商品信息
     * @return
     */
    @PostMapping("/goods/update")
    ResponseResult updateGoodsItem();

    /**
     * 更改商品下架状态
     * @param id
     * @return
     */
    @PostMapping("/goods/{id}/pullOff")
    ResponseResult soldOutGoodsById(@PathVariable("id") Long id);

    /**
     * 删除商品
     * @param id
     * @return
     */
    @PostMapping("/goods/{id}/delete")
    ResponseResult removeGoodsById(@PathVariable("id") Long id);

    /**
     * 通过 ID数组 获得一个商品信息的  数组
     * @param ids
     * @return
     */
    @GetMapping("/goods/goodsSkuList")
    ResponseResult<List<OrderGoodsSkuItemDto>> listGoodsSkuListByIdList(@RequestParam("ids") String ids);

    /**
     * 通过ID获得指定商品信息
     * @param id
     * @return
     */
    @GetMapping("/goods/goodsSkuItem")
    ResponseResult<OrderGoodsSkuItemDto> listGoodsSkuItemById(@RequestParam("id") Long id);

}
