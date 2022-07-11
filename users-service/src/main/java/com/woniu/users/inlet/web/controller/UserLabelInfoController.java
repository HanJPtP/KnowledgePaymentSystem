package com.woniu.users.inlet.web.controller;

import com.woniu.users.service.impl.UserLabelInfoServiceImpl;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
@RequiredArgsConstructor
public class UserLabelInfoController {
    private final UserLabelInfoServiceImpl userLabelInfoService;
    private final RedisTemplate<String,String> redisTemplate;

    /**
     *  新增用户标签信息
     * @param uid
     * @param labelIds 标签id，逗号隔开
     * @return
     */
    @GetMapping("userlabel/insert")
    public ResponseResult<Void> addUserLabelInfo(@RequestParam("uid") Long uid,
                                                 @RequestParam("labelIds") String labelIds,
                                                 @RequestParam("uuids") String uuids
                                                 ){
        if(!StringUtils.hasText(uuids)){
            // 每页携带uuids,不能新增
            return new ResponseResult<>(500,"新增失败");
        }
        // 删除redis中的数据 labelToken为键
//        Long rs = redisTemplate.opsForSet().remove("labelToken", uuids);
        Boolean rs = redisTemplate.delete(uuids);
        if(!rs){
            // 删除失败，不能重复新增
            return new ResponseResult<>(500,"新增失败");
        }
        // 调用新增方法
        try {
            userLabelInfoService.insertUserLabel(uid, labelIds);
            return new ResponseResult<>(200,"新增成功");
        }catch (Exception e){
            // 新增失败，恢复redis中的值
            redisTemplate.opsForValue().set(uuids, "insert", 60, TimeUnit.MINUTES);
            return new ResponseResult<>(500,"新增失败");
        }


    }
}
