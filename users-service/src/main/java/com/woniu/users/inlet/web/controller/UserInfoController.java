package com.woniu.users.inlet.web.controller;

import com.woniu.inlet.web.fo.UserLoginStatusFo;
import com.woniu.message.entity.dto.MessageDTO;
import com.woniu.outlet.dao.dto.UserLoginToUserDto;
import com.woniu.users.inlet.web.client.ServiceMessageClient;
import com.woniu.users.inlet.web.client.UserStatusClient;
import com.woniu.users.inlet.web.dto.*;
import com.woniu.users.outlet.dao.mysql.mapper.UserInfoMapper;
import com.woniu.users.outlet.dao.mysql.po.UserGrowthLog;
import com.woniu.users.outlet.dao.mysql.po.UserInfo;
import com.woniu.users.outlet.dao.mysql.po.UserPointLog;
import com.woniu.users.service.impl.*;
import com.woniu.users.util.PageInfo;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserInfoController {

    private final UserInfoServiceImpl userInfoService;

    private final ServiceMessageClient serviceMessageClient;

    private final UserLabelInfoServiceImpl userLabelInfoService;

    private final UserMemberInfoServiceImpl userMemberInfoService;

    private final UserPlusInfoServiceImpl userPlusInfoService;

    private final UserBehaviorDtoServiceImpl userBehaviorDtoService;

    private final UserPointLogServiceImpl userPointLogService;

    private final UserGrowthLogServiceImpl userGrowthLogService;

    private final UserStatusClient userStatusClient;

    private final UserInfoMapper userInfoMapper;




    /**
     *  根据uid查询
     * @param uid
     * @return
     */
    @GetMapping("userinfo/uid")
    public ResponseResult<Object> getByUid(@RequestParam("uid") Long uid){
        UserInfo userInfo = userInfoService.getUserByUid(uid);
        return new ResponseResult<Object>(200,"查询结果",userInfo);

    }

    /**
     *  多条件查询
     * @param pageNo
     * @param pageSize
     * @param levelName
     * @param startRegisterTime
     * @param endRegisterTime
     * @param labelName
     * @param uid
     * @param username
     * @param phone
     * @return
     */
    @GetMapping("userinfo/list")
    public ResponseResult<Object> listUserInfo(@RequestParam(value = "pageNo",required = false,defaultValue = "1") Long pageNo,
                                               @RequestParam(value = "pageSize",required = false,defaultValue = "10") Long pageSize,
                                               @RequestParam(value = "levelName",required = false,defaultValue = "") String levelName,
                                               @RequestParam(value = "registerTime",required = false,defaultValue = "") String startRegisterTime,
                                               @RequestParam(value = "endRegisterTime",required = false,defaultValue = "") String endRegisterTime,
                                               @RequestParam(value = "labelName",required = false,defaultValue = "") String labelName,
                                               @RequestParam(value = "uid",required = false,defaultValue = "-1") Long uid,
                                               @RequestParam(value = "username",required = false,defaultValue = "") String username,
                                               @RequestParam(value = "phone",required = false,defaultValue = "") String phone){
        //Long pageNo,Long pageSize, String levelName, Date registerTime,
          //      String labelName, Long uid, String username, String phone

        ResponseResult<Object> responseResult = null;

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date startTime = null;
//        Date endTime = null;
//        if(!startRegisterTime.equals("") || !endRegisterTime.equals("")) {
//            try {
//                startTime = sdf.parse(startRegisterTime);
//                endTime = sdf.parse(endRegisterTime);
//            } catch (ParseException e) {
//                e.printStackTrace();
//                return new ResponseResult<>(400, "参数错误：注册时间格式有误");
//
//            }
//        }

        // 得到标签数组
        String[] labelNameStr = null;
        if(!labelName.equals("")){
            labelNameStr = labelName.split(",");
        }

//        levelName = levelNameStr[levelNameStr.length-1];
//        labelName = labelNameStr[labelNameStr.length-1];;
//        for(int i = 0; i < levelNameStr.length-1; i++){
//            levelName +=  "," + levelNameStr[i];
//        }
//        for(int i = 0; i < labelNameStr.length-1; i++){
//            levelName += "," + labelNameStr[i];
//        }
//        System.out.println(levelName + "=--=" + labelName);

        PageInfo<UserMsg> pageInfo = userInfoService.listUserInfo(pageNo, pageSize, levelName, startRegisterTime,endRegisterTime, labelNameStr, uid, username, phone);
        if(pageInfo.getData().size() > 0){
            // 将labelName 标签存入一个数组中
            for (int i = 0; i < pageInfo.getData().size(); i++) {
                List<String> list = new ArrayList<>();
                for (UserMsg user2 : pageInfo.getData()) {
                    if (pageInfo.getData().get(i).getUid() == user2.getUid()){
                        list.add(user2.getLabelName());
                    }
                }
                pageInfo.getData().get(i).setLabelNameArr(list);
            }
            for (UserMsg user2 : pageInfo.getData()) {
                user2.setLabelName("");
            }

            //使用hashset集合去重
            List<UserMsg> listNew=new ArrayList<>(new HashSet(pageInfo.getData()));
            pageInfo.setTotalNum(listNew.size());
            pageInfo.setData(listNew);

            return new ResponseResult<>(200,"查询成功",pageInfo);
        }else{
            return new ResponseResult<>(400,"查无数据");
        }
    }


    /**
     *  根据用户uid查询用户邮箱
     * @param uids
     * @return
     */
    @GetMapping("userinfo/getemail")
    public ResponseResult<Object> getEmailByUid(@RequestParam(value = "uids",required = false,defaultValue = "") String uids){

        if(uids.length()<=0){
            return new ResponseResult<>(400,"参数错误");
        }
        // 字符串转集合
        try {
            List<Long> uidList = Arrays.asList(uids.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());

            System.out.println("uidList" + uidList.toString());
            // 查询用户状态是否正常使用，得到有效的用户id集合




            List<String> emailList = userInfoService.getEmailByUid(uidList);
            if(emailList.size() > 0){
                return new ResponseResult<>(200,"查询成功",emailList);
            }else{
                return new ResponseResult<>(400,"查无数据");
            }

        }catch (NumberFormatException e){
            log.error(e.getMessage());
            return new ResponseResult<>(400,"参数格式错误");

        }


    }

    /**
     *  修改用户状态,发送修改消息
     * @param uid
     * @param status
     * @return
     */
    @GetMapping("user/updatestatus")
    public ResponseResult<Void> updateUserStatus(@RequestParam(value = "uid",required = false,defaultValue = "-1") Long uid,@RequestParam(value = "status",defaultValue = "-1",required = false) Integer status){
       // 查询是否有该用户uid
        ResponseResult<UserLoginToUserDto> userInfoItemById = userStatusClient.getUserInfoItemById(uid);
        if(userInfoItemById.getData() == null){
            return new ResponseResult<>(400,"修改失败，未查到该用户状态信息");
        }
        if(status == -1 || (status != 1 && status != 0) || uid == -1){
            return new ResponseResult<>(400,"输入参数错误");
        }

        UserLoginStatusFo statusFo = new UserLoginStatusFo();
        statusFo.setId(uid);
        statusFo.setStatus(status+"");
        // 修改状态
        ResponseResult responseResult = userStatusClient.updateUserLoginInfoItem(statusFo);

        MessageDTO messageDTO = new MessageDTO("user.status", "user-status", uid + ":" + status, "a123@12.com");
        serviceMessageClient.saveMessage(messageDTO);
        return new ResponseResult<>(200,"success");
    }

    /**
     *  根据用户Uid查询用户详细信息
     * @param uid
     * @return
     */
    @GetMapping("user/userdetail")
    public ResponseResult<Object> getUserDetail(@RequestParam("uid") Long uid){
        // 判断该用户是否存在
        if(userInfoMapper.selectById(uid) == null){
            return new ResponseResult<>(400,"查无此用户");
        }

        // 得到用户 的基本消息
        UserDetailDto userDetailDto = userInfoService.getUserDetailByUid(uid);
        // 根据uid查询标签名集合
        List<String> listLabelName = userLabelInfoService.listLabelName(uid);
        // 得到会员等级信息,包括了总积分和成长值
        UserMemberInfoDto userMemberInfoDto = userMemberInfoService.getUserMemberDtoByUid(uid);
        // 得到付费会员信息，可能为null
        UserPlusInfoDto userPlusInfoDto = userPlusInfoService.getByUid(uid);
        // 得到行为统计信息
        UserBehaviorDto userBehaviorDto = userBehaviorDtoService.getUserBehaviorByUid(uid);
        // 得到积分记录表
        List<UserPointLog> userPointLogList = userPointLogService.getPointLogByUid(uid);
        // 得到成长值记录表
        List<UserGrowthLog> growthLogList = userGrowthLogService.getByUid(uid);

        // 将查询的信息存入map
        Map map = new HashMap();
        map.put("userDetailDto",userDetailDto);
        map.put("listLabelName",listLabelName);
        map.put("userMemberInfoDto",userMemberInfoDto);
        map.put("userPlusInfoDto",userPlusInfoDto);
        map.put("userBehaviorDto",userBehaviorDto);
        map.put("userPointLogList",userPointLogList);
        map.put("growthLogList",growthLogList);

        log.info(map.toString());

        return new ResponseResult<>(200,"查询成功",map);
    }



    /**
     *  新增用户信息
     * @param userInfo
     * @return
     */
    @PostMapping("userinfo/add")
    public ResponseResult<Object> addUserInfo(@RequestBody UserInfo userInfo){
        System.out.println("sadd----------------" + userInfo.toString());
       if(userInfo.getUsername() == null || userInfo.getUsername().length()<=0
        || userInfo.getUserImg() == null || userInfo.getUserImg().length()<=0
       || userInfo.getAge() == null || userInfo.getAge()<=0
       || userInfo.getPhone() == null || userInfo.getPhone().length()<=0
       || userInfo.getGender() == null || (userInfo.getGender()!=0 && userInfo.getGender()!= 1)
       || userInfo.getEmail() == null || userInfo.getEmail().length()<=0
       || userInfo.getJob() == null || userInfo.getJob().length()<=0
       || userInfo.getRegisterTime() == null ){
           return new ResponseResult<>(400,"数据填写有误，新增失败");
       }

        int rs = userInfoService.addUserInfo(userInfo);
        if(rs > 0){
            return new ResponseResult<>(200,"新增成功");
        } else{
            return new ResponseResult<>(500,"新增失败");
        }
    }
}
