package com.woniu.outlet.dao.convert;

import com.woniu.outlet.dao.dto.GoodsSkuDto;
import com.woniu.outlet.dao.dto.PageInfo;
import com.woniu.outlet.dao.mysql.po.GoodsSku;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class GoodsSkuToGoodsSkuDto {

    public static List<GoodsSkuDto> turnGoodsSkuToGoodsSkuDto(List<GoodsSku> goodsSkuList) {
        List<GoodsSkuDto> goodsSkuDtoList = new ArrayList<>();
        if (goodsSkuList.size() > 0) {
            for (GoodsSku goodsSku : goodsSkuList) {
                GoodsSkuDto goodsSkuDto = new GoodsSkuDto(goodsSku.getId(), goodsSku.getDescription(), goodsSku.getPrice(), goodsSku.getCreateTime(), goodsSku.getOrderno());
                goodsSkuDtoList.add(goodsSkuDto);
            }
        }
        return goodsSkuDtoList;
    }

    public static PageInfo<GoodsSkuDto> turnGoodsSkuToGoodsSkuDtoPageInfo(Page<GoodsSku> goodsSkuPage) {
        List<GoodsSkuDto> goodsSkuDtoList = new ArrayList<>();
        if (goodsSkuPage.getContent().size() > 0) {
            for (GoodsSku goodsSku : goodsSkuPage.getContent()) {
                GoodsSkuDto goodsSkuDto = new GoodsSkuDto(goodsSku.getId(), goodsSku.getDescription(), goodsSku.getPrice(), goodsSku.getCreateTime(), goodsSku.getOrderno());
                goodsSkuDtoList.add(goodsSkuDto);
            }
        }
        PageInfo<GoodsSkuDto> pageInfo = new PageInfo<>();
        pageInfo.setData(goodsSkuDtoList);
        pageInfo.setPageno(goodsSkuPage.getNumber());
        pageInfo.setPageSize(goodsSkuPage.getSize());
        pageInfo.setPages(goodsSkuPage.getTotalPages());
        pageInfo.setTotalNum(goodsSkuPage.getTotalElements());
        return pageInfo;
    }

}
