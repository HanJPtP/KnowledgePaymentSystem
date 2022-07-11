package com.woniu.users.inlet.web.controller;

import com.woniu.users.inlet.web.dto.UserLevelDto;
import com.woniu.users.outlet.dao.mysql.mapper.UserLevelMapper;
import com.woniu.users.outlet.dao.mysql.po.UserLevel;
import com.woniu.users.service.impl.UserLevelDtoServiceImpl;
import com.woniu.users.service.impl.UserLevelServiceImpl;
import com.woniu.users.util.PageInfo;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 *  用户等级信息类
 */
@RestController
@RequiredArgsConstructor
public class UserLevelController {
    private final UserLevelServiceImpl userLevelService;

    private final UserLevelDtoServiceImpl userLevelDtoService;

    private final UserLevelMapper userLevelMapper;

    @GetMapping("userlevel/query")
    public ResponseResult<Object> listUserLevel(@RequestParam(value="levelName",defaultValue = "",required = false) String levelName,
                                                @RequestParam(value = "pageNo",required = false,defaultValue = "1") Long pageNo,
                                                @RequestParam(value = "pageSize",required = false,defaultValue = "5") Long pageSize){
        PageInfo<UserLevel> pageInfo = userLevelService.listUserLevel(pageNo, pageSize, levelName);
        if(pageInfo.getData().size() > 0){
            return new ResponseResult<>(200,"查询成功",pageInfo);
        } else {
            return new ResponseResult<>(400,"查无数据");
        }
    }

    /**
     *  新增会员等级
     * @param userLevelDto
     * @return
     */
    @PostMapping("userlevel/add")
    public ResponseResult<Void> addUserLevel(@RequestBody UserLevelDto userLevelDto){
        if(userLevelDto.getMinPoint() == null || userLevelDto.getMaxPoint() == null
        || userLevelDto.getMinPoint()>=userLevelDto.getMaxPoint()){
            // 最小成长值不能比最大成长值大
            return new ResponseResult<>(400,"数据填写有误");
        }
        int rs = userLevelDtoService.addUserLevelDto(userLevelDto);
        if(rs > 0){
            return new ResponseResult<>(200,"新增成功");
        } else {
            return new ResponseResult<>(500,"新增失败");
        }
    }

    /**
     *  查询需要显示的修改页面数据
     * @param levelId
     * @return
     */
    @GetMapping("userlevel/showupdate")
    public ResponseResult<Object> showUpdateUserLevel(@RequestParam("levelId") Long levelId){
        if(userLevelMapper.selectById(levelId) == null){
            return new ResponseResult<>(400,"查无数据");
        }
        UserLevelDto userleveldto = userLevelDtoService.getUserLevelById(levelId);
        return new ResponseResult<>(200,userleveldto);
    }

    /**
     *  修改会员等级信息
     * @param userLevelDto
     * @return
     */
    @PostMapping("userlevel/update")
    public ResponseResult<Void> updateUserLevel(@RequestBody UserLevelDto userLevelDto){
        int rs = userLevelDtoService.updateUserLevelDto(userLevelDto);
        if(rs > 0) {
            return new ResponseResult<>(200,"修改成功");
        } else {
            return new ResponseResult<>(500,"修改失败");
        }
    }
}
