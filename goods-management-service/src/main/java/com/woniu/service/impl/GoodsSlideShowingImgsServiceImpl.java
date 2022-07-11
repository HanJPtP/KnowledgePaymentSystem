package com.woniu.service.impl;

import com.woniu.outlet.dao.mysql.po.GoodsSkuSlideshowingimgs;
import com.woniu.outlet.mysql.mapper.GoodsSkuSlideshowingimgsMapper;
import com.woniu.service.IGoodsSlideShowingImgsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsSlideShowingImgsServiceImpl implements IGoodsSlideShowingImgsService {

    private final GoodsSkuSlideshowingimgsMapper goodsSkuSlideshowingimgsMapper;

    /**
     * 通过商品 goods_sku ID 获得商品轮播图列表
     * @param skuid
     * @return
     */
    @Override
    public List<GoodsSkuSlideshowingimgs> listGoodsSlideShowingImgsByGoodsSkuId(Long skuid) {
        return goodsSkuSlideshowingimgsMapper.listGoodsSlideShowingImgsByGoodsSkuId(skuid);
    }
}
