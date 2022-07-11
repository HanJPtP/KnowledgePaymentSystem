package com.woniu.outlet.mysql.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniu.outlet.dao.mysql.po.TimingSale;
import com.woniu.util.EditTimeUtil;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author ThinkPad
* @description 针对表【timing_sale】的数据库操作Mapper
* @createDate 2022-06-15 16:57:42
* @Entity com.woniu.outlet.dao.mysql.po.TimingSale
*/
public interface TimingSaleMapper extends BaseMapper<TimingSale> {

    /**
     * 保存 timing_sale 数组信息
     * @param timingSaleList
     * @return
     */
    int saveTimingSaleList(@Param("timingSaleList") List<TimingSale> timingSaleList);

    /**
     * 根据 goods_sku ID 获得其对应的 timing_sale 信息
     * @param id
     * @return
     */
    default TimingSale getTimingSaleByGoodsSkuId(Long id) {
        QueryWrapper<TimingSale> eq = new QueryWrapper<TimingSale>()
                .eq("sku_id", id);
        return this.selectOne(eq);
    }

    /**
     * 删除 sku_id 在该数组中的所有数据
     * @param goodsSkuIdList
     * @return
     */
    default int removeTimingSaleBySkuIds(List<Long> goodsSkuIdList) {
        QueryWrapper<TimingSale> wrapper = new QueryWrapper<TimingSale>()
                .in("sku_id", goodsSkuIdList);
        return this.delete(wrapper);
    }

    default List<TimingSale> listTimingSaleTask(Date startDate, Date endDate) {
        QueryWrapper<TimingSale> eq = new QueryWrapper<TimingSale>()
                .between("start_time", startDate, endDate)
                .or()
                .between("end_time", startDate, endDate)
                .or()
                .between("start_time", startDate, endDate)
                .isNull("end_time");
        return this.selectList(eq);
    }

}




