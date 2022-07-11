package com.woniu.outlet.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.GoodsSkuSlideshowingimgs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ThinkPad
* @description 针对表【goods_sku_slideShowingImgs】的数据库操作Mapper
* @createDate 2022-06-16 12:14:43
* @Entity com.woniu.outlet.dao.mysql.po.GoodsSkuSlideshowingimgs
*/
public interface GoodsSkuSlideshowingimgsMapper extends BaseMapper<GoodsSkuSlideshowingimgs> {

    /**
     * 根据商品 goods_sku 的 ID 获得该商品的所有轮播图列表
     * @param skuid
     * @return
     */
    default List<GoodsSkuSlideshowingimgs> listGoodsSlideShowingImgsByGoodsSkuId(Long skuid) {
        QueryWrapper<GoodsSkuSlideshowingimgs> eq = new QueryWrapper<GoodsSkuSlideshowingimgs>()
                .eq("sku_id", skuid);
        return this.selectList(eq);
    }

    /**
     * 添加商品 goods_sku 对应的轮播图列表
     * @param goodsSkuSlideshowingimgsList
     * @return
     */
    int saveGoodsSkuSlideShowingImgsList(@Param("goodsSkuSlideshowingimgsList") List<GoodsSkuSlideshowingimgs> goodsSkuSlideshowingimgsList);

    default int removeSlideShowingImgsBySkuId(List<Long> goodsSkuIdList) {
        QueryWrapper<GoodsSkuSlideshowingimgs> wrapper = new QueryWrapper<GoodsSkuSlideshowingimgs>()
                .in("sku_id", goodsSkuIdList);
        return this.delete(wrapper);
    }

}




