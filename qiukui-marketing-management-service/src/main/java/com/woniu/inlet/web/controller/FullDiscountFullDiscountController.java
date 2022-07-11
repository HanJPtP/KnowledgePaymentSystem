package com.woniu.inlet.web.controller;

import com.woniu.fo.FullDiscountFullDiscountFo;
import com.woniu.outlet.dao.mysql.pojo.FullDiscountFullDiscount;
import com.woniu.outlet.dao.mysql.pojo.PageInfo;
import com.woniu.service.impl.FullDiscountFullDiscountServiceImpl;
import com.woniu.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qk
 * @since 2022-06-19-17:37
 */
@RestController
@RequiredArgsConstructor
public class FullDiscountFullDiscountController {

    private final FullDiscountFullDiscountServiceImpl fullDiscountFullDiscountService;


    /**
     * 新建满X元减活动
     *
     * @param fullDiscountFullDiscountFo
     * @return
     */
    @PostMapping("/FullDiscountFullDiscount/add")
    public ResponseResult<Void> addFullDiscountFullDiscount(@RequestBody FullDiscountFullDiscountFo fullDiscountFullDiscountFo) {
        ResponseResult<Void> result = null;
        try {
            fullDiscountFullDiscountService.addFullDiscountFullDiscount(fullDiscountFullDiscountFo);
            result = new ResponseResult<>(200, "添加成功!");
        } catch (RuntimeException e) {
            result = new ResponseResult<>(500, "添加失败!");
        }
        return result;
    }

    //根据状态和名称  多条件查询满减满折活动列表 并分页
    @PostMapping("/FullDiscountFullDiscount/list")
    public ResponseResult<Object> getFullDiscountFullDiscountList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize,
                                                                  @RequestParam(value = "activestatus", required = false, defaultValue = "-1") Integer activestatus,
                                                                  @RequestParam(value = "eventname", required = false, defaultValue = "") String eventname) {

        PageInfo<FullDiscountFullDiscount> pageInfo = fullDiscountFullDiscountService.getFullDiscountFullDiscountList(pageNo, pageSize, activestatus, eventname);
        ResponseResult<Object> result = null;
        if (pageInfo != null) {
            result = new ResponseResult<>(200, "ok", pageInfo);
        } else {
            result = new ResponseResult<>(500, "没有查到数据!");
        }
        return result;
    }
}
