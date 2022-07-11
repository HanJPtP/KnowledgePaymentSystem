package com.woniu.users.inlet.web.controller;

import com.woniu.users.outlet.dao.mysql.po.PointRule;
import com.woniu.users.service.impl.PointRuleServiceImpl;
import com.woniu.users.util.PageInfo;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PointRuleController {
    private final PointRuleServiceImpl pointRuleService;

    /**
     *  多条件查询
     * @param pageNo
     * @param pageSize
     * @param isDiscount
     * @param status
     * @return
     */
    @GetMapping("pointrule/list")
    public ResponseResult<Object> listPointRule(@RequestParam(value = "pageNo",defaultValue = "1",required = false) Long pageNo,
                                                @RequestParam(value = "pageSize",defaultValue = "5",required = false) Long pageSize,
                                                @RequestParam(value = "isDiscount",defaultValue = "-1",required = false) Integer isDiscount,
                                                @RequestParam(value = "status",defaultValue = "-1",required = false) Integer status){
        PageInfo<PointRule> pageInfo = pointRuleService.listPointRule(pageNo, pageSize, isDiscount, status);
        if(pageInfo.getData().size() > 0){
            return new ResponseResult<>(200,"查询成功",pageInfo);
        }else {
            return new ResponseResult<>(400,"查无数据");
        }

    }

    /**
     *  新增
     * @param pointRule
     * @return
     */
    @PostMapping("pointrule/add")
    public ResponseResult<Void> addPointRule(@RequestBody PointRule pointRule){
        if(pointRule.getSkuId() == null || pointRule.getSkuId() <= 0 ||
        pointRule.getIsDiscount() == null || (pointRule.getIsDiscount()!=0 && pointRule.getIsDiscount()!=1)
        || pointRule.getDeduction() == null || pointRule.getDeduction() <=0 || pointRule.getDeduction()>=Integer.MAX_VALUE){
            return new ResponseResult<>(400,"填写数据错误");
        }
        int rs = pointRuleService.addPointRule(pointRule);
        if(rs > 0){
            return new ResponseResult<>(200,"新增成功");
        }else{
            return new ResponseResult<>(500,"新增失败");
        }
    }

    /**
     *  显示修改页面数据
     * @param id
     * @return
     */
    @PostMapping("pointrule/showupdate")
    public ResponseResult<Object> showUpdate(@RequestParam("id") Long id){
        PointRule pointRule = pointRuleService.getPointRuleById(id);
        if(pointRule == null){
            return new ResponseResult<>(400,"查无数据");
        }else {
            return new ResponseResult<>(200,pointRule);
        }
    }

    /**
     *  修改
     * @param pointRule
     * @return
     */
    @PostMapping("pointrule/update")
    public ResponseResult<Void> updatePointRule(@RequestBody PointRule pointRule){
        int rs = pointRuleService.updatePointRule(pointRule);
        if(rs > 0){
            return new ResponseResult<>(200,"修改成功");
        } else{
            return new ResponseResult<>(500,"修改失败");
        }
    }
}
