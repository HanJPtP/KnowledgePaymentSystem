package com.woniu.outlet.dao.mysql.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 分页数据类
 */
@Data
public class PageInfo<E> {
    private List<E> data; // 每页数据
    private long totalNum;// 数据总条数
    private long totalPage;// 总页数
    private long pageSize;// 每页的数量
    private long pageNo;// 当前页码

    public PageInfo(IPage<E> page) {
        this.data=page.getRecords();//保存查询数据
        this.totalNum=page.getTotal();//保存总条数
        this.totalPage=page.getPages();//保存总页数
        this.pageNo=page.getCurrent();//保存当前页
        this.pageSize=page.getSize();//保存每页数量
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "data=" + data +
                ", totalNum=" + totalNum +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}