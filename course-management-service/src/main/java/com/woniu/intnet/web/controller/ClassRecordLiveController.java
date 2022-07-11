package com.woniu.intnet.web.controller;


import com.woniu.intnet.web.fo.ClassRecordLiveFo;
import com.woniu.intnet.web.fo.ClassRecordLiveUpdateFo;
import com.woniu.outnet.dao.pojo.ClassRecordLive;
import com.woniu.outnet.dao.pojo.PageInfo;
import com.woniu.service.impl.ClassRecordLiveServiceImpl;
import com.woniu.util.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class ClassRecordLiveController {

    private final ClassRecordLiveServiceImpl classRecordLiveService;

    private final StringRedisTemplate redisTemplate;

    /**
     * 添加直播
     *
     * @param classRecordLiveFo
     * @return
     */
    @SneakyThrows
    @PostMapping("/live/add")
    public ResponseEntity<String> addClassRecordLive(@RequestBody ClassRecordLiveFo classRecordLiveFo,
                                                     HttpServletRequest request) {
        if (redisTemplate.opsForSet().remove(request.getHeader("x-username") + "-token", classRecordLiveFo.getRestFul()) != 0) {
            int i = classRecordLiveService.insertClassRecordLive(classRecordLiveFo);
            if (i > 0) {
                return new ResponseEntity<>("添加成功", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("添加失败", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<String>("重复添加或伪造", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改所有属性的直播
     *
     * @param classRecordLiveUpdateFo
     * @return
     */
    @SneakyThrows
    @PostMapping("/live/all/update")
    public ResponseEntity<String> updateClassRecordLive(@RequestBody ClassRecordLiveUpdateFo classRecordLiveUpdateFo) {
        int i = classRecordLiveService.updateClassRecordLiveAll(classRecordLiveUpdateFo);
        if (i > 0) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改开始时间
     *
     * @param id
     * @param startTime
     * @return
     */
    @SneakyThrows
    @PostMapping("/live/startTime/update")
    public ResponseEntity<String> updateClassRecordLiveTheStartTime(@RequestParam(value = "id", required = false, defaultValue = "0") Long id,
                                                                    @RequestParam(value = "starttime", required = false, defaultValue = "") String startTime) {

        Date date = null;
        if (startTime.length() > 0) {
            date = DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", startTime);
            if (date.getTime() < new Date().getTime()) {
                return new ResponseEntity<>("该时间小于当前时间", HttpStatus.BAD_REQUEST);
            } else {
                int i = classRecordLiveService.updateClassRecordLiveWhileBeginShow(id, date);
                if (i > 0) {
                    return new ResponseEntity<>("修改成功", HttpStatus.OK);
                } else if (i == 0) {
                    return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
                } else {
                    return new ResponseEntity<>("该数据不存在", HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>("时间不符合规则", HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * 修改结束时间
     *
     * @param id
     * @param endTime
     * @return
     */
    @SneakyThrows
    @PostMapping("/live/endTime/update")
    public ResponseEntity<String> updateClassRecordLiveTheEndTime(@RequestParam(value = "id", required = false, defaultValue = "0") Long id,
                                                                  @RequestParam(value = "endtime", required = false, defaultValue = "") String endTime) {

        Date date = null;
        if (endTime.length() > 0) {
            date = DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", endTime);
            if (date.getTime() < new Date().getTime()) {
                return new ResponseEntity<>("该时间小于当前时间", HttpStatus.BAD_REQUEST);
            }else{
                int i = classRecordLiveService.updateClassRecordLiveWhileEndShow(id, date);
                if (i > 0) {
                    return new ResponseEntity<>("修改成功", HttpStatus.OK);
                } else if (i == 0) {
                    return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
                } else if(i==-2){
                    return new ResponseEntity<>("该直播还未开始,无法修改结束时间,若要修改请联系管理员修改",HttpStatus.BAD_REQUEST);
                }else {
                    return new ResponseEntity<>("该数据不存在", HttpStatus.BAD_REQUEST);
                }
            }
        }else{
            return new ResponseEntity<>("时间不符合规则", HttpStatus.BAD_REQUEST);
        }

    }

    @SneakyThrows
    @PostMapping("/live/status/update")
    public ResponseEntity<String> updateClassRecordLiveStatus(@RequestParam(value = "id", required = false, defaultValue = "0") Long id,
                                                              @RequestParam(value = "status", required = false, defaultValue = "") String status) {
        int i = classRecordLiveService.updateClassRecordLiveStatusById(id, status);

        if (i > 0) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 多条件分页查询
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    @SneakyThrows
    @GetMapping("/class/show")
    public ResponseEntity<PageInfo<ClassRecordLive>> showClassRecordLivePage(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                             @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
                                                                             @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                             @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                                                                             @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime) {

        if (startTime.length() > 0 && endTime.length() > 0) {
            if (DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", startTime).getTime() > DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", endTime).getTime()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        PageInfo<ClassRecordLive> pageInfo = classRecordLiveService.listAllClassRecordLiveByChooseAndPage(pageNo, pageSize, name, startTime, endTime);
        if (pageInfo.getData().size() > 0) {
            return new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
