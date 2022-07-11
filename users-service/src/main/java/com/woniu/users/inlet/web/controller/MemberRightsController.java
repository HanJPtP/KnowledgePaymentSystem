package com.woniu.users.inlet.web.controller;

import com.woniu.knowledgepayment.commons.util.MinioUtils;
import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import com.woniu.users.outlet.dao.mysql.po.MemberRights;
import com.woniu.users.service.impl.MemberRightsServiceImpl;
import com.woniu.users.util.PageInfo;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class MemberRightsController {
    private final MemberRightsServiceImpl memberRightsService;
    private final MinioUtils minioUtils;

    @GetMapping("rights/list")
    public ResponseResult<Object> listMemberRights(@RequestParam(value = "rightName",defaultValue = "",required = false) String rightName,
                                                   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Long pageNo,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "5") Long pageSize){
        PageInfo<MemberRights> pageInfo = memberRightsService.listMemberRights(pageNo, pageSize, rightName);
        if(pageInfo.getData().size() > 0){
            return new ResponseResult<>(200,"查询成功",pageInfo);
        } else{
            return new ResponseResult<>(400,"查无数据");
        }
    }

    /**
     *  新增
     * @param memberRights
     * @return
     */
    @PostMapping("rights/add")
    public ResponseResult<Void> addMemberRights(@RequestBody MemberRights memberRights){
        memberRights.setRightId(new SnowFlakeGenerator().nextId());
        memberRights.setStatus(1);
        memberRights.setModifiedTime(new Date());
        if(memberRights.getRightName() == null || memberRights.getRightName().length()<=0
        || memberRights.getRightImg() == null || memberRights.getRightImg().length()<=0
        || memberRights.getRightInfo() == null || memberRights.getRightInfo().length()<=0){
            return new ResponseResult<>(400,"参数填写有误");
        }

        int rs = memberRightsService.addMemberRights(memberRights);
        if(rs > 0){
            return new ResponseResult<>(200,"新增成功");
        } else {
            return new ResponseResult<>(500,"新增失败");
        }

    }

    /**
     *  显示修改页面数据
     * @param rightId
     * @return
     */
    @GetMapping("rights/showupdate")
    public ResponseResult<Object> showUpdate(@RequestParam("rightId") Long rightId){
        MemberRights memberRights = memberRightsService.selectByRightId(rightId);
        if(memberRights != null){
            return new ResponseResult<>(200,memberRights);
        } else {
            return new ResponseResult<>(400,"查无数据");
        }
    }

    /**
     *  修改
     * @param memberRights
     * @return
     */
    @PostMapping("rights/update")
    public ResponseResult<Void> updateMemberRights(@RequestBody MemberRights memberRights){
        memberRights.setModifiedTime(new Date());

        int rs = memberRightsService.updateMemberRights(memberRights);
        if(rs > 0){
            return new ResponseResult<>(200,"修改成功");
        } else {
            return new ResponseResult<>(500,"修改失败");
        }

    }

}
