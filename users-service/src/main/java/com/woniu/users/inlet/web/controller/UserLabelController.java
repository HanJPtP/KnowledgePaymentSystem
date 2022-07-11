package com.woniu.users.inlet.web.controller;

import com.woniu.users.inlet.web.dto.LabelInfoDto;
import com.woniu.users.outlet.dao.mysql.po.UserLabel;
import com.woniu.users.service.impl.UserLabelServiceImpl;
import com.woniu.users.util.PageInfo;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class UserLabelController {
    private final UserLabelServiceImpl userLabelService;

    private final RedisTemplate<String,String> redisTemplate;

    /**
     *  查询所有标签对象
     * @return
     */
    @PostMapping("userlabel/list")
    public ResponseResult<Object> getUserLabelList(){
        // 调用付费，查所有标签
        List<UserLabel> userLabelList = userLabelService.listUserLabel();
        // 生成的uuid
        String uuids = UUID.randomUUID().toString();
        // 向跳转的新增页面传一个值，避免并发新增
//        redisTemplate.opsForSet().add("labelToken",uuids);
        redisTemplate.opsForValue().set(uuids, "insert", 60, TimeUnit.MINUTES);

        Map mapInfo = new HashMap();
        mapInfo.put("userLabelList",userLabelList);
        mapInfo.put("uuids",uuids);
        return new ResponseResult<>(200,mapInfo);
    }

    /**
     *  用户标签多条件查询
     * @param pageNo
     * @param pageSize
     * @param labelType
     * @param labelName
     * @param labelRank
     * @return
     */
    @GetMapping("userlabel/query")
    public ResponseResult<Object> listUserLabel(@RequestParam(value = "pageNo",required = false,defaultValue = "1") Long pageNo,
                                                @RequestParam(value = "pageSize",required = false,defaultValue = "5") Long pageSize,
                                                @RequestParam(value = "labelType",required = false,defaultValue = "") String labelType,
                                                @RequestParam(value = "labelName",required = false,defaultValue = "") String labelName,
                                                @RequestParam(value = "labelRank",required = false,defaultValue = "") String labelRank){
        PageInfo<LabelInfoDto> pageInfo = userLabelService.listLabelIngoDto(pageNo, pageSize, labelType, labelName, labelRank);
        if(pageInfo.getData().size() > 0){
            return new ResponseResult<>(200,"查询成功",pageInfo);
        } else {
            return new ResponseResult<>(400,"查无数据");
        }
    }

    /**
     *  新增标签  标签名不同（区分重复新增）
     * @return
     */
    @PostMapping("userlabel/add")
    public ResponseResult<Void> addUserLabel(@RequestBody UserLabel userLabel) {
        if (userLabel.getLabelName() == null || userLabel.getLabelType() == null || userLabel.getLabelRank() == null) {
            return new ResponseResult<>(400, "新增的数据不完整");
        }
        // 赋值默认使用状态
        userLabel.setStatus(1);
        userLabel.setLabelId(null);
        //调用新增
        int rs = userLabelService.addUserLabel(userLabel);
        if(rs > 0){
            return new ResponseResult<>(200,"新增成功");
        }else{
            return new ResponseResult<>(500,"新增失败");
        }
    }


    /**
     *  修改标签
     * @param userLabel
     * @return
     */
    @PostMapping("userlabel/update")
    public ResponseResult<Void> updateUserLabel(@RequestBody UserLabel userLabel){
        int rs = userLabelService.updateUserLabel(userLabel);
        if(rs > 0){
            return new ResponseResult<>(200,"修改成功");
        }else{
            return new ResponseResult<>(500,"修改失败");
        }


    }

}
