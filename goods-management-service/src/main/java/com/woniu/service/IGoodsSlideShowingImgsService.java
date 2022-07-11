package com.woniu.service;

import com.woniu.outlet.dao.mysql.po.GoodsSkuSlideshowingimgs;

import java.util.List;

public interface IGoodsSlideShowingImgsService {

    // 通过商品 goods_sku ID 获得商品轮播图列表
    List<GoodsSkuSlideshowingimgs> listGoodsSlideShowingImgsByGoodsSkuId(Long skuid);

}
