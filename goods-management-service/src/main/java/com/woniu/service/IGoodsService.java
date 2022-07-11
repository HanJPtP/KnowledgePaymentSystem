package com.woniu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outlet.dao.dto.*;
import com.woniu.outlet.dao.mysql.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodsService {

    // 分页多条件查询商品 goods sku
    IPage<GoodsSkuDto> listGoodsSkuItem(String saleable, String title,
                                        String startTime, String endTime,
                                        Integer pageno, Integer pageSize);

    // 查询商品分类级别 第一类级别
    List<GoodsSorting> listFirstMenuGoodsSorting();

    // 通过sku ID 获得相关信息 dto 订单调用API
    OrderGoodsSkuItemDto getGoodsSkuItemById(Long id);

    // 通过 sku ids 查询 OrderGoodsSkuItemDto 数组
    List<OrderGoodsSkuItemDto> listGoodsSkuListByIdList(List<Long> ids);

    // 通过 sku ID 查询所有 参数列表以及 参数值列表
    List<CategoryToParamsToValueDto> listAllParamsToValuesById(Long id);

    // 添加 goods sku 数组
    int saveGoodsSkuList(List<GoodsSku> goodsSkuList,
                         List<Long> goodsSkuIdList,
                         List<GoodsSkuValue> goodsSkuValueList,
                         Long spgid,
                         List<TimingSale> timingSaleList,
                         List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList);

    // 更改商品 goods_sku 状态, 删除状态 下架状态
    int pullOffGoodsSkuItem(GoodsSku goodsSku);

    // 查询指定商品的分类信息, 最后一级信息
    GoodsSorting getGoodsSortingItemByGoodsSkuId(Long id);

    // 通过 goods_sku ID 获得一个显示到更新界面的 goods_sku_update_dto
    GoodsSkuShowUpdateDto getGoodsSkuShowaUpdateItemById(Long id);

    // 通过 goods_spu ID 获得显示到更新界面的 goods_sku_update_dto 的列表
    List<GoodsSkuShowUpdateDto> listGoodsSkuShowaUpdateItemById(Long id);

    // 获得当前商品所有的参数和参数值
    List<GoodsParamsAndIdShowUpdateDto> listGoodsSkuOwnedParamsAndValueItem(Long id);

    // 通过商品 goods_sku ID 获得对应的所有的参数以及对应参数值
    List<CategoryToParamsToValueDto> listGoodsSkuParamsAndValueByGoodsSkuId(Long skuid);

    // 修改商品 goods_sku 信息
    int updateGoodsSkuList(List<GoodsSku> goodsSkuList,
                           List<Long> goodsSkuIdList,
                           List<GoodsSkuValue> goodsSkuValueList,
                           Long spgid,
                           List<TimingSale> timingSaleList,
                           List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList);

    // 通过 timing_sale ID 来更改对应的 goods_sku 中的上架信息
    int updateGoodsSkuValidItem(Long timingSaleId, String status);

    /**
     * 查询所有满足条件的 goods_sku 信息
     *
     * @param saleable
     * @param title
     * @param startTime
     * @param endTime
     * @return
     */
    IPage<GoodsSku> listAllGoodsSkuItem(String saleable, String title,
                                       String startTime, String endTime,
                                       Integer pageno, Integer pageSize);

    /**
     * 查询 ID 在该集合中的 goodssku 信息
     * @param ids
     * @return
     */
    List<GoodsSku> listGoodsSkuByIds(List<Long> ids);

    /**
     * 通过商品 goodssku ID 获得 goodssku
     * @param id
     * @return
     */
    GoodsSku getGoodsSkuById(Long id);

    int deleteGoodsSkuById(GoodsSku goodsSku);

}
