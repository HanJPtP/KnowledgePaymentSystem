package com.woniu.intnet.web.controller;


import com.woniu.intnet.web.vo.WatchUserTimeByCurrencyVo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.service.impl.ClassCurrencyWatchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ClassCurrencyWatchController {

    private final ClassCurrencyWatchServiceImpl classCurrencyWatchService;

    private final StringRedisTemplate redisTemplate;

    private final RestFulTokenController restFulTokenController;

    /**
     * 查看数据(什么人学习了什么课花了多长时间)
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @GetMapping("/show/currencywatch/{id}")
    public ResponseResult<List<WatchUserTimeByCurrencyVo>> showWatchUserTimeByCurrencyVoByUserid(@PathVariable("id") Long id) {
        ResponseResult<List<WatchUserTimeByCurrencyVo>> responseResult = null;
        List<WatchUserTimeByCurrencyVo> watchUserTimeByCurrencyVos = classCurrencyWatchService.listWatchUserTimeByCurrencyVoByUserid(id);
        if (watchUserTimeByCurrencyVos.size() > 0) {
            responseResult = new ResponseResult<>(200, "ok", watchUserTimeByCurrencyVos);
        } else {
            responseResult = new ResponseResult<>(6001, "no", watchUserTimeByCurrencyVos);
        }
        return responseResult;
    }


    /**
     * 进入时候新增对象
     *
     * @param userid
     * @param crlid
     * @return
     */
    @SneakyThrows
    @PostMapping("/add/incurrencywatch")
    public ResponseEntity<String> getTheInsertId(@RequestParam("userid") Long userid, @RequestParam("crlid") Long crlid, @RequestParam("restFul") String addToken, HttpServletRequest request) {
        if (redisTemplate.opsForSet().remove(request.getHeader("x-username") + "-token", addToken) != 0) {
            Long id = 0l;
            try {
                id=classCurrencyWatchService.insertClassCurrencyWatch(userid, crlid);
            } catch (Exception e) {
                ResponseEntity<Map<String, String>> mapResponseEntity = restFulTokenController.queryCouldAdd(request);
                String restFul = mapResponseEntity.getBody().get("restFul");
                return new ResponseEntity<String>(restFul, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (id > 0) {
                return new ResponseEntity<>(id.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("没有数据", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("重复添加或伪造", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改时间
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @PostMapping("/update/quitcurrencywatch")
    public ResponseEntity<Void> getUpdateById(@RequestParam(value = "id", required = false, defaultValue = "0l") Long id) {
        int i = classCurrencyWatchService.updateClassCurrencyWatchById(id);
        if (i > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
