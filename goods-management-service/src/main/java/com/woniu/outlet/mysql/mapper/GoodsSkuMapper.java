package com.woniu.outlet.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.outlet.dao.dto.*;
import com.woniu.outlet.dao.mysql.po.GoodsSku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
* @author ThinkPad
* @description 针对表【goods_sku】的数据库操作Mapper
* @createDate 2022-06-13 16:11:03
* @Entity com.woniu.outlet.dao.mysql.po.GoodsSku
*/
@Repository
public interface GoodsSkuMapper extends BaseMapper<GoodsSku> {

    /**
     * 分页多条件查询商品 goods sku
     * @param page
     * @param saleable
     * @param title
     * @param startTime
     * @param endTime
     * @return
     */
    IPage<GoodsSkuDto> listGoodsSkuItem(Page page,
                                        @Param("saleable") String saleable,
                                        @Param("title") String title,
                                        @Param("startTime") String startTime,
                                        @Param("endTime") String endTime);

    // 查询所有的 goodsskudto 信息, 存到 elasticsearch 中
    @Select("select gs.id, gs.description, gs.price, gs.create_time, gs.orderno from goods_sku gs")
    List<GoodsSkuDto> listAllGoodsSkuDto();

    /**
     * 查询所有满足条件的 goods_sku 信息
     * @param page
     * @param saleable
     * @param title
     * @param startTime
     * @param endTime
     * @return
     */
    IPage<GoodsSku> listAllGoodsSkuItem(Page page,
                                       @Param("saleable") String saleable,
                                       @Param("title") String title,
                                       @Param("startTime") String startTime,
                                       @Param("endTime") String endTime);

    /**
     * 通过sku ID 获得相关信息 dto 订单调用API
     * @param id
     * @return
     */
    OrderGoodsSkuItemDto getGoodsSkuItemById(Long id);

    List<ParamsValueDto> getGoodsSkuParmsMap(Long id);

    /**
     * 通过 sku ids 查询 OrderGoodsSkuItemDto 数组
     * @param ids
     * @return
     */
    List<OrderGoodsSkuItemDto> listGoodsSkuListByIdList(@Param("ids") List<Long> ids);

    /**
     * 添加 goods sku 数组
     * @param goodsSkuList
     * @return
     */
    int saveGoodsSkuList(@Param("goodsSkuList") List<GoodsSku> goodsSkuList);

    /**
     * 通过 goods_sku ID 获得一个显示到更新界面的 goods_sku_update_dto
     * @param id
     * @return
     */
    GoodsSkuShowUpdateDto getGoodsSkuShowaUpdateItemById(@Param("id") Long id);

    List<GoodsSkuShowUpdateDto> listGoodsSkuShowaUpdateItemById(@Param("id") Long id);

    /**
     * 获得当前商品所有的参数和参数值
     * @param id
     * @return
     */
    List<GoodsParamsAndIdShowUpdateDto> listGoodsSkuOwnedParamsAndValueItem(Long id);

    /**
     * 循环更新商品 goods_sku 信息
     * @param goodsSkuList
     * @return
     */
    default int updateGoodsSkuList(List<GoodsSku> goodsSkuList) {
        int i = 0;
        if (goodsSkuList.size() > 0) {
            for (GoodsSku goodsSku : goodsSkuList) {
                this.updateById(goodsSku);
                i ++;
            }
        }
        return i;
    }

    /**
     * 通过 timing_sale ID 来更改对应的 goods_sku 中的上架信息
     * @param timingSaleId
     * @param status
     * @return
     */
    int updateGoodsSkuValidItem(@Param("timingSaleId") Long timingSaleId,
                                @Param("status") String status,
                                @Param("lastUpdateTime")Date lastUpdateTime);

    default List<GoodsSku> listGoodsSkuByIds(List<Long> ids) {
        QueryWrapper<GoodsSku> eq = new QueryWrapper<GoodsSku>()
                .in("id", ids);
        return this.selectList(eq);
    }

    @Update("UPDATE goods_sku SET is_deleted= #{goodsSku.isDeleted} WHERE id= #{goodsSku.id} and is_deleted not in ('y') ")
    int deleteGoodsSkuById(@Param("goodsSku") GoodsSku goodsSku);

    @Update("UPDATE goods_sku SET saleable= #{goodsSku.saleable} WHERE id= #{goodsSku.id} and saleable not in ('1')")
    int pullOffGoodsSkuItem(@Param("goodsSku") GoodsSku goodsSku);

}




