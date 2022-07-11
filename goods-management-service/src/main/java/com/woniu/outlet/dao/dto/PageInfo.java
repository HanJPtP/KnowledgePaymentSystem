package com.woniu.outlet.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {

    private List<T> data;
    private Integer pageno;
    private Integer pageSize;
    private Long totalNum;
    private Integer pages;

}
