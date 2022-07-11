package com.woniu.intnet.web.controller;


import com.woniu.intnet.timer.RestFulTokenTimer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class RestFulTokenController {

    private final StringRedisTemplate redisTemplate;

    private final ScheduledExecutorService scheduledExecutorService;



    /**
     * 获得幂等token
     * @return
     */
    @RequestMapping("/restFul/get")
    public ResponseEntity<Map<String,String>> queryCouldAdd(HttpServletRequest request){
        String s = UUID.randomUUID().toString();
        String header = request.getHeader("x-username");
        //超时失效
        redisTemplate.opsForSet().add(header+"-token",s);
        HashMap<String, String> map = new HashMap<String, String>();
        //交给线程池,线程池帮你删除
        scheduledExecutorService.schedule(new RestFulTokenTimer(header+"-token",s,redisTemplate), 1, TimeUnit.HOURS);
        map.put("restFul",s);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
