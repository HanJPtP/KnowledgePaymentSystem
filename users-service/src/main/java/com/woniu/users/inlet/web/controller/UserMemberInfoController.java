package com.woniu.users.inlet.web.controller;

import com.woniu.users.outlet.dao.mysql.po.UserCoupon;
import com.woniu.users.service.impl.UserMemberInfoServiceImpl;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserMemberInfoController {
    private final UserMemberInfoServiceImpl userMemberInfoService;

    private final RedisTemplate<String,String> redisTemplate;

    /**
     *  查询用户积分
     * @param uid
     * @return
     */
    @GetMapping("usermember/getpoints")
    public ResponseResult<Object> getUserMemberPoints(@RequestParam("uid") Long uid){
        // 查询用户积分
        List<Integer> points = userMemberInfoService.getPoints(uid);
        if(points.size() > 0) {
            return new ResponseResult<>(200, points.get(0));
        }else{
            // 没有积分，默认为0
            return new ResponseResult<>(400,"查无数据");
        }
    }

    /**
     *  修改用户会员积分，新增积分日志
     * @param uid
     * @param prepoints
     * @param nowpoints
     * @param note
     * @return
     */
    @GetMapping("usermember/updatepoints")
    public ResponseResult<Object> updateUserMemberPoints(@RequestParam("uid") Long uid,
                                                        @RequestParam("prepoints") Integer prepoints,
                                                        @RequestParam(value = "nowpoints",defaultValue = "0",required = false) Integer nowpoints,
                                                         @RequestParam("note") String note){
        if(uid == null || uid<=0 || prepoints == null || prepoints<=0 || nowpoints<=0){
            return new ResponseResult<>(400,"参数填写有误");
        }
        int rs = userMemberInfoService.updateUserMemberPoints(uid, prepoints, nowpoints, note);
        if(rs > 0){
            return new ResponseResult<>(200,"修改积分成功");
        } else {
            return new ResponseResult<>(500,"修改积分失败");
        }

    }

    /**
     *  查询用户成长值
     * @param uid
     * @return
     */
    @GetMapping("usermember/getgrowthnum")
    public ResponseResult<Object> getUserMemberGrowthNum(@RequestParam("uid") Long uid){
        // 查询用户积分
        List<Integer> growthNum = userMemberInfoService.getGrowthNum(uid);
        if(growthNum.size() > 0) {
            return new ResponseResult<>(200, growthNum.get(0));
        }else{
            // 没有积分，默认为0
            return new ResponseResult<>(400,"查无数据");
        }
    }

    /**
     *  修改用户会员成长值，新增成长值日志
     * @param uid
     * @param preGrowthNum
     * @param nowGrowthNum
     * @param note
     * @return
     */
    @GetMapping("usermember/updategrowthnum")
    public ResponseResult<Object> updateUserMemberGrowthNum(@RequestParam("uid") Long uid,
                                                         @RequestParam("preGrowthNum") Integer preGrowthNum,
                                                         @RequestParam(value = "nowGrowthNum",defaultValue = "0",required = false) Integer nowGrowthNum,
                                                         @RequestParam("note") String note){
        if(uid == null || uid<=0 || preGrowthNum == null || preGrowthNum<=0 || nowGrowthNum<=0){
            return new ResponseResult<>(400,"参数填写有误");
        }
        int rs = userMemberInfoService.updateUserMemberGrowthNum(uid, preGrowthNum, nowGrowthNum, note);
        if(rs > 0){
            return new ResponseResult<>(200,"修改成长值成功");
        } else {
            return new ResponseResult<>(500,"修改成长值失败");
        }

    }

    /**
     *  查询所有优惠券信息
     * @return
     */
    @PostMapping("usermember/listcoupon")
    public ResponseResult<Object> selectAllCoupon(){
        List<UserCoupon> userCoupons = userMemberInfoService.selectAllCoupon();
        if(userCoupons.size() > 0){
            return new ResponseResult<>(200,userCoupons);
        } else {
            return new ResponseResult<>(400,"查无数据");
        }
    }
}
