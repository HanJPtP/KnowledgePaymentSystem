package com.woniu.users.inlet.web.controller;

import com.woniu.users.outlet.dao.mysql.mapper.MemberPlusTypeMapper;
import com.woniu.users.outlet.dao.mysql.po.MemberPlusType;
import com.woniu.users.service.impl.MemberPlusTypeServiceImpl;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberPlusTypeController {
    private final MemberPlusTypeServiceImpl memberPlusTypeService;

    private final MemberPlusTypeMapper memberPlusTypeMapper;

    /**
     *  查询所有
     * @return
     */
    @PostMapping("memberplus/list")
    public ResponseResult<Object> listMemberPlusType(){
        List<MemberPlusType> memberPlusTypeList = memberPlusTypeService.listMemberPlusType();
        if(memberPlusTypeList.size() > 0){
            return new ResponseResult<>(200,memberPlusTypeList);
        } else {
            return new ResponseResult<>(500,"查无数据");
        }
    }

    /**
     *  新增
     * @param memberPlusType
     * @return
     */
    @PostMapping("memberplus/add")
    public ResponseResult<Void> addMemberPlusType(@RequestBody MemberPlusType memberPlusType) {
        if(memberPlusType.getBgcolor() == null || memberPlusType.getBgcolor().length()<=0
        || memberPlusType.getPlusname() == null || memberPlusType.getPlusname().length()<=0
        || memberPlusType.getDescription() == null || memberPlusType.getDescription().length()<=0
        || memberPlusType.getEfficientTime() == null || memberPlusType.getEfficientTime() >= Integer.MAX_VALUE
        || memberPlusType.getEfficientTime() <= 0 || memberPlusType.getPrice() == null ||
        memberPlusType.getPrice() <= 0){
            return new ResponseResult<>(400,"参数填写有误");
        }
        int rs = memberPlusTypeService.addMemberPlusType(memberPlusType);
        if(rs > 0){
            return new ResponseResult<>(200,"新增成功");
        } else {
            return new ResponseResult<>(500,"新增失败");
        }
    }


    /**
     *  显示修改页面数据
     * @param plusTypeid
     * @return
     */
    @GetMapping("memberplus/showupdate")
    public ResponseResult<Object> showUpdate(@RequestParam("plusTypeid") Long plusTypeid){
        // 判断id是否存在
        if(memberPlusTypeMapper.selectById(plusTypeid) == null){
            return new ResponseResult<>(400,"查无数据");
        }
        return new ResponseResult<>(200,memberPlusTypeService.getByPlusTypeid(plusTypeid));
    }

    /**
     *  修改
     * @param memberPlusType
     * @return
     */
    @PostMapping("memberplus/update")
    public ResponseResult<Void> updateMemberPlusType(@RequestBody MemberPlusType memberPlusType) {
        int rs = memberPlusTypeService.updateMemberPlusType(memberPlusType);
        if(rs > 0){
            return new ResponseResult<>(200,"修改成功");
        } else {
            return new ResponseResult<>(500,"修改失败");
        }
    }

}
