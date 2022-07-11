package com.woniu.intnet.web.controller;


import com.woniu.intnet.web.fo.ClassCurrencyMsgAddFo;
import com.woniu.intnet.web.fo.ClassCurrencyMsgUpdateFo;
import com.woniu.outnet.dao.pojo.ClassCurrencyMsg;
import com.woniu.outnet.dao.pojo.PageInfo;
import com.woniu.service.impl.ClassCurrencyMsgServiceImpl;
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
public class ClassCurrencyMsgController {


    private final ClassCurrencyMsgServiceImpl classCurrencyMsgService;

    private final StringRedisTemplate redisTemplate;

    /**
     * 多条件分页查询
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @param startPrice
     * @param endPrice
     * @param startTime
     * @param endTime
     * @return
     */
    @SneakyThrows
    @GetMapping("/show/currency")
    public ResponseEntity<PageInfo<ClassCurrencyMsg>> showClassCurrencyMsgPage(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                               @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                               @RequestParam(value = "startPrice", required = false, defaultValue = "-1") Double startPrice,
                                                                               @RequestParam(value = "endPrice", required = false, defaultValue = "-1") Double endPrice,
                                                                               @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                                                                               @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime) {
        //如果最大小于最小
        if (startPrice > 0 && endPrice > 0) {
            if (startPrice > endPrice) {
                //返回请求错误
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        //如果最大小于最小
        if (startTime.length() > 0 && endTime.length() > 0) {
            //返回请求错误
            if (DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", startTime).getTime() > DateFormatUtils.StringToDate("yyyy-MM-dd HH:mm:ss", endTime).getTime()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        PageInfo<ClassCurrencyMsg> pageInfo = classCurrencyMsgService.listClassCurrencyMsgByChooseAndPage(pageNo, pageSize, name, startPrice, endPrice, startTime, endTime);
        if (pageInfo.getData().size() > 0) {
            return new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 添加
     *
     * @param classCurrencyMsgAddFo
     * @return
     */
    @SneakyThrows
    @PostMapping("/currency/add")
    public ResponseEntity<String> addClassCurrencyMsg(@RequestBody ClassCurrencyMsgAddFo classCurrencyMsgAddFo,
                                                      HttpServletRequest request) {
        if (redisTemplate.opsForSet().remove(request.getHeader("x-username") + "-token", classCurrencyMsgAddFo.getRestFul()) != 0) {
            //判断输入的定时上下架时间是否符合规则
            if (classCurrencyMsgAddFo.getEndtime() != null && classCurrencyMsgAddFo.getStarttime() != null) {
                if (classCurrencyMsgAddFo.getEndtime().getTime() <= (classCurrencyMsgAddFo.getStarttime().getTime() + 360000l) && classCurrencyMsgAddFo.getEndtime().getTime() >= (classCurrencyMsgAddFo.getStarttime().getTime() - 360000l)) {
                    return new ResponseEntity<>("错误请求参数", HttpStatus.BAD_REQUEST);
                }
                if (classCurrencyMsgAddFo.getStarttime().getTime() <= (classCurrencyMsgAddFo.getEndtime().getTime() + 360000l) && classCurrencyMsgAddFo.getStarttime().getTime() >= (classCurrencyMsgAddFo.getEndtime().getTime() - 360000l)) {
                    return new ResponseEntity<>("错误请求参数", HttpStatus.BAD_REQUEST);
                }
            }
            if (classCurrencyMsgAddFo.getStarttime() != null) {
                if (classCurrencyMsgAddFo.getStarttime().getTime() <= new Date().getTime()) {
                    return new ResponseEntity<>("定时上架时间不能是过去时间", HttpStatus.BAD_REQUEST);
                } else if (classCurrencyMsgAddFo.getStarttime().getTime() <= (new Date().getTime() + 360000l)) {
                    return new ResponseEntity<>("定时上架时间距当前间隔太短", HttpStatus.BAD_REQUEST);
                }
            }
            if (classCurrencyMsgAddFo.getEndtime() != null) {
                if (classCurrencyMsgAddFo.getEndtime().getTime() <= new Date().getTime()) {
                    return new ResponseEntity<>("定时下架时间不能是过去时间", HttpStatus.BAD_REQUEST);
                } else if (classCurrencyMsgAddFo.getEndtime().getTime() <= (new Date().getTime() + 360000l)) {
                    return new ResponseEntity<>("定时下架时间距当前间隔太短", HttpStatus.BAD_REQUEST);
                }
            }

            int i = classCurrencyMsgService.insertClassCurrencyMsg(classCurrencyMsgAddFo);
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
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    @SneakyThrows
    @PostMapping("/currency/status/update")
    public ResponseEntity<String> updateClassCurrencyMsgStatus
    (@RequestParam(value = "id", required = false, defaultValue = "0") Long id,
     @RequestParam(value = "status", required = false, defaultValue = "") String status) {
        int i = classCurrencyMsgService.updateClassCurrencyMsgStatus(id, status);
        if (i > 0) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改所有信息
     *
     * @param classCurrencyMsgUpdateFo
     * @return
     */
    @SneakyThrows
    @PostMapping("/currency/all/update")
    public ResponseEntity<String> updateClassCurrencyMsgAll(@RequestBody ClassCurrencyMsgUpdateFo
                                                                    classCurrencyMsgUpdateFo) {
        //判断输入的定时上下架时间是否符合规则
        if (classCurrencyMsgUpdateFo.getEndtime() != null && classCurrencyMsgUpdateFo.getStarttime() != null) {
            if (classCurrencyMsgUpdateFo.getEndtime().getTime() <= (classCurrencyMsgUpdateFo.getStarttime().getTime() + 360000l) && classCurrencyMsgUpdateFo.getEndtime().getTime() >= (classCurrencyMsgUpdateFo.getStarttime().getTime() - 360000l)) {
                return new ResponseEntity<>("错误请求参数", HttpStatus.BAD_REQUEST);
            }
            if (classCurrencyMsgUpdateFo.getStarttime().getTime() <= (classCurrencyMsgUpdateFo.getEndtime().getTime() + 360000l) && classCurrencyMsgUpdateFo.getStarttime().getTime() >= (classCurrencyMsgUpdateFo.getEndtime().getTime() - 360000l)) {
                return new ResponseEntity<>("错误请求参数", HttpStatus.BAD_REQUEST);
            }
        }
        if (classCurrencyMsgUpdateFo.getStarttime() != null) {
            if (classCurrencyMsgUpdateFo.getStarttime().getTime() <= new Date().getTime()) {
                return new ResponseEntity<>("定时上架时间不能是过去时间", HttpStatus.BAD_REQUEST);
            } else if (classCurrencyMsgUpdateFo.getStarttime().getTime() <= (new Date().getTime() + 360000l)) {
                return new ResponseEntity<>("定时上架时间距当前间隔太短", HttpStatus.BAD_REQUEST);
            }
        }
        if (classCurrencyMsgUpdateFo.getEndtime() != null) {
            if (classCurrencyMsgUpdateFo.getEndtime().getTime() <= new Date().getTime()) {
                return new ResponseEntity<>("定时下架时间不能是过去时间", HttpStatus.BAD_REQUEST);
            } else if (classCurrencyMsgUpdateFo.getEndtime().getTime() <= (new Date().getTime() + 360000l)) {
                return new ResponseEntity<>("定时下架时间距当前间隔太短", HttpStatus.BAD_REQUEST);
            }
        }

        int i = classCurrencyMsgService.updateClassCurrencyMsgAllCanNull(classCurrencyMsgUpdateFo);
        if (i > 0) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查单个
     */
    @SneakyThrows
    @GetMapping("/currency/{id}/search")
    public ResponseEntity<ClassCurrencyMsg> showOnlyOne(@PathVariable("id") Long id) {
        ClassCurrencyMsg classCurrencyMsg = classCurrencyMsgService.listClassCurrencyMsgById(id);
        if (classCurrencyMsg != null) {
            return new ResponseEntity<>(classCurrencyMsg, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改定时上架的时间
     *
     * @param startTime
     * @param id
     * @return
     */
    @SneakyThrows
    @PostMapping("/currency/starttime/update")
    public ResponseEntity<String> updateClassCurrencyMsgStartTimeById
    (@RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
     @RequestParam(value = "id", required = false, defaultValue = "0") Long id) {

        int i = classCurrencyMsgService.updateClassCurrencyMsgStartTimeById(id, startTime);
        if (i > 0) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else if (i == 0) {
            return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (i == -1) {
            return new ResponseEntity<>("该用不存在", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("时间间隔太短", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 修改定时下架的时间
     *
     * @param endTime
     * @param id
     * @return
     */
    @SneakyThrows
    @PostMapping("/currency/endtime/update")
    public ResponseEntity<String> updateClassCurrencyMsgEndTimeById
    (@RequestParam(value = "endTime", required = false, defaultValue = "") String endTime,
     @RequestParam(value = "id", required = false, defaultValue = "0") Long id) {

        int i = classCurrencyMsgService.updateClassCurrencyMsgEndTimeById(id, endTime);
        if (i > 0) {
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else if (i == 0) {
            return new ResponseEntity<>("修改失败", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (i == -1) {
            return new ResponseEntity<>("该用不存在", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("时间间隔太短", HttpStatus.BAD_REQUEST);
        }
    }
}
