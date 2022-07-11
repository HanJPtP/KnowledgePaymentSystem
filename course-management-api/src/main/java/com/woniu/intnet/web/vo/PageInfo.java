package com.woniu.intnet.web.vo;

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