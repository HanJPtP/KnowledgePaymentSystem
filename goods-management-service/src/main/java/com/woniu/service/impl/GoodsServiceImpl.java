package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outlet.dao.dto.*;
import com.woniu.outlet.dao.mysql.po.*;
import com.woniu.outlet.mysql.mapper.*;
import com.woniu.service.IGoodsService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsServiceImpl implements IGoodsService {

    private final GoodsSkuMapper goodsSkuMapper;
    private final TimingSaleMapper timingSaleMapper;
    private final GoodsSkuSpuMapper goodsSkuSpuMapper;
    private final GoodsSortingMapper goodsSortingMapper;
    private final GoodsCategoryMapper goodsCategoryMapper;
    private final GoodsSkuValueMapper goodsSkuValueMapper;
    private final GoodsSkuSlideshowingimgsMapper goodsSkuSlideshowingimgsMapper;

    /**
     * 分页多条件查询商品 goods sku
     * @param saleable
     * @param title
     * @param startTime
     * @param endTime
     * @param pageno
     * @param pageSize
     * @return
     */
    @Override
    public IPage<GoodsSkuDto> listGoodsSkuItem(String saleable, String title, String startTime, String endTime, Integer pageno, Integer pageSize) {
        Page<GoodsSku> page = new Page<>(pageno, pageSize);
        IPage<GoodsSkuDto> iPage = goodsSkuMapper.listGoodsSkuItem(page, saleable, title, startTime, endTime);
        return iPage;
    }

    /**
     * 查询商品分类 分级
     * @return
     */
    @Override
    public List<GoodsSorting> listFirstMenuGoodsSorting() {
        List<GoodsSorting> goodsSortings = goodsSortingMapper.listAllGoodsSorting();

        // 查询一级菜单
        List<GoodsSorting> firstMenu = new ArrayList<>();
        for (GoodsSorting goodsSorting : goodsSortings) {
            if (goodsSorting.getParentId() == null) {
                firstMenu.add(goodsSorting);
            }
        }

        // 设置二级菜单
        for (GoodsSorting goodsSorting : firstMenu) {
            goodsSorting.setChildrenList(getGoodsSortingChildrenList(goodsSorting.getId(), goodsSortings));
        }

        return firstMenu;
    }

    /**
     * 通过 goods sku ID 查询 OrderGoodsSkuItemDto
     * @param id
     * @return
     */
    @Override
    public OrderGoodsSkuItemDto getGoodsSkuItemById(Long id) {
        OrderGoodsSkuItemDto orderGoodsSkuItemDto = goodsSkuMapper.getGoodsSkuItemById(id);
        return orderGoodsSkuItemDto;
    }

    /**
     * 通过 goods sku 的 ID 数组 查询 OrderGoodsSkuItemDto
     * @param ids
     * @return
     */
    @Override
    public List<OrderGoodsSkuItemDto> listGoodsSkuListByIdList(List<Long> ids) {
        List<OrderGoodsSkuItemDto> dtoList = goodsSkuMapper.listGoodsSkuListByIdList(ids);
        return dtoList;
    }

    /**
     * 通过 goods spu ID 获得其所有 param 数组, 以及对应 值
     * @param id
     * @return
     */
    @Override
    public List<CategoryToParamsToValueDto> listAllParamsToValuesById(Long id) {
        List<CategoryToParamsToValueDto> params = goodsCategoryMapper.listAllParamsToValuesById(id);
        return params;
    }

    /**
     * 添加 goods_sku, goods_sku_value, goods_sku_spu 数组对象
     * @param goodsSkuList
     * @param goodsSkuIdList
     * @param goodsSkuValueList
     * @return
     */
    @Override
    public int saveGoodsSkuList(List<GoodsSku> goodsSkuList,
                                List<Long> goodsSkuIdList,
                                List<GoodsSkuValue> goodsSkuValueList,
                                Long spgid,
                                List<TimingSale> timingSaleList,
                                List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList) {
        goodsSkuSpuMapper.saveGoodsSkuSpuList(goodsSkuIdList, spgid);
        goodsSkuValueMapper.saveGoodsSkuAndValueList(goodsSkuValueList);
        if (timingSaleList.size() > 0) {
            timingSaleMapper.saveTimingSaleList(timingSaleList);
        }
        if (goodsSkuSlideshowingimgsList.size() > 0)
            goodsSkuSlideshowingimgsMapper.saveGoodsSkuSlideShowingImgsList(goodsSkuSlideshowingimgsList);
        return goodsSkuMapper.saveGoodsSkuList(goodsSkuList);
    }

    /**
     * 更新商品 goods_sku
     * @param goodsSku
     * @return
     */
    @Override
    public int pullOffGoodsSkuItem(GoodsSku goodsSku) {
        return goodsSkuMapper.pullOffGoodsSkuItem(goodsSku);
    }

    /**
     * 查询指定商品的分类信息, 最后一级信息
     * @param id
     * @return
     */
    @Override
    public GoodsSorting getGoodsSortingItemByGoodsSkuId(Long id) {
        GoodsSorting goodsSorting = goodsSortingMapper.getGoodsSortingByGoodsSkuId(id);
        return goodsSorting;
    }

    /**
     * 通过 goods_sku ID 获得一个显示到更新界面的 goods_sku_update_dto
     * @param id
     * @return
     */
    @Override
    public GoodsSkuShowUpdateDto getGoodsSkuShowaUpdateItemById(Long id) {
        GoodsSkuShowUpdateDto goodsSkuShowUpdateDto = goodsSkuMapper.getGoodsSkuShowaUpdateItemById(id);
        return goodsSkuShowUpdateDto;
    }

    @Override
    public List<GoodsSkuShowUpdateDto> listGoodsSkuShowaUpdateItemById(Long id) {
        List<GoodsSkuShowUpdateDto> goodsSkuShowUpdateDtos = goodsSkuMapper.listGoodsSkuShowaUpdateItemById(id);
        return goodsSkuShowUpdateDtos;
    }

    /**
     * 获得当前商品所有的参数和参数值
     * @param id
     * @return
     */
    @Override
    public List<GoodsParamsAndIdShowUpdateDto> listGoodsSkuOwnedParamsAndValueItem(Long id) {
        List<GoodsParamsAndIdShowUpdateDto> goodsParamsAndIdShowUpdateDtoList = goodsSkuMapper.listGoodsSkuOwnedParamsAndValueItem(id);
        return goodsParamsAndIdShowUpdateDtoList;
    }

    /**
     * 通过商品 goods_sku ID 获得对应的所有的参数以及对应参数值
     * @param skuid
     * @return
     */
    @Override
    public List<CategoryToParamsToValueDto> listGoodsSkuParamsAndValueByGoodsSkuId(Long skuid) {
        List<CategoryToParamsToValueDto> categoryToParamsToValueDtoList = goodsCategoryMapper.listGoodsSkuParamsAndValueByGoodsSkuId(skuid);
        return categoryToParamsToValueDtoList;
    }

    /**
     * 修改商品 goods_sku 信息
     * @param goodsSkuList
     * @param goodsSkuIdList
     * @param goodsSkuValueList
     * @param spgid
     * @param timingSaleList
     * @param goodsSkuSlideshowingimgsList
     * @return
     */
    @Override
    public int updateGoodsSkuList(List<GoodsSku> goodsSkuList, List<Long> goodsSkuIdList, List<GoodsSkuValue> goodsSkuValueList, Long spgid, List<TimingSale> timingSaleList, List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList) {
        // 删除原来 goods_sku_spu 绑定信息
        // 添加一条 goods_sku_spu 绑定数据
        goodsSkuSpuMapper.removeGoodsSkuSpuList(goodsSkuIdList);
        goodsSkuSpuMapper.saveGoodsSkuSpuList(goodsSkuIdList, spgid);

        // 更改 goods_sku 信息
        int i = goodsSkuMapper.updateGoodsSkuList(goodsSkuList);

        // 删除 goods_sku_slideshowingimgs 绑定信息
        // 添加 goods_sku_slideshowingimgs 绑定信息
        goodsSkuSlideshowingimgsMapper.removeSlideShowingImgsBySkuId(goodsSkuIdList);
        goodsSkuSlideshowingimgsMapper.saveGoodsSkuSlideShowingImgsList(goodsSkuSlideshowingimgsList);

        // 删除 goods_sku_value 当前绑定的 参数
        // 添加 goods_sku_value 绑定参数信息
        goodsSkuValueMapper.removeGoodsSkuValueListByGoodsSkuIds(goodsSkuIdList);
        goodsSkuValueMapper.saveGoodsSkuAndValueList(goodsSkuValueList);

        // 删除定时任务
        // 添加定时任务
        timingSaleMapper.removeTimingSaleBySkuIds(goodsSkuIdList);
        timingSaleMapper.saveTimingSaleList(timingSaleList);
        return i;
    }

    /**
     * 通过 timing_sale ID 来更改对应的 goods_sku 中的上架信息
     * @param timingSaleId
     * @param status
     * @return
     */
    @Override
    public int updateGoodsSkuValidItem(Long timingSaleId, String status) {
        return goodsSkuMapper.updateGoodsSkuValidItem(timingSaleId, status, new Date());
    }

    /**
     * 查询所有满足条件的 goods_sku 信息
     * @param saleable
     * @param title
     * @param startTime
     * @param endTime
     * @return
     */
    public IPage<GoodsSku> listAllGoodsSkuItem(String saleable, String title,
                                              String startTime, String endTime,
                                              Integer pageno, Integer pageSize) {
        Page<GoodsSku> page = new Page<>(pageno, pageSize);
        IPage<GoodsSku> iPage = goodsSkuMapper.listAllGoodsSkuItem(page, saleable, title, startTime, endTime);
        return iPage;
    }

    /**
     * 查询 ID 在该集合中的 goodssku 集合
     * @param ids
     * @return
     */
    @Override
    public List<GoodsSku> listGoodsSkuByIds(List<Long> ids) {
        return goodsSkuMapper.listGoodsSkuByIds(ids);
    }

    /**
     * 通过 goodssku ID 获得 goodssku
     * @param id
     * @return
     */
    @Override
    public GoodsSku getGoodsSkuById(Long id) {
        return goodsSkuMapper.selectById(id);
    }

    @Override
    public int deleteGoodsSkuById(GoodsSku goodsSku) {
        return goodsSkuMapper.deleteGoodsSkuById(goodsSku);
    }

    /**
     * 用于递归循环查询商品分类下的子级别
     * @param parentId
     * @param goodsSortings
     * @return
     */
    public List<GoodsSorting> getGoodsSortingChildrenList(Long parentId, List<GoodsSorting> goodsSortings) {
        List<GoodsSorting> secondMenu = new ArrayList<>();
        for (GoodsSorting goodsSorting : goodsSortings) {
            if (goodsSorting.getParentId() == parentId) {
                secondMenu.add(goodsSorting);
            }
        }
        for (GoodsSorting goodsSorting : secondMenu) {
            goodsSorting.setChildrenList(getGoodsSortingChildrenList(goodsSorting.getId(), goodsSortings));
        }
        if (secondMenu.size() <= 0) {
            return null;
        }
        return secondMenu;
    }
}
