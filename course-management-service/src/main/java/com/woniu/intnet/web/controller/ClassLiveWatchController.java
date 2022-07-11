package com.woniu.intnet.web.controller;


import com.woniu.intnet.web.vo.WatchUserTimeByLiveVo;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.service.impl.ClassLiveWatchServiceImpl;
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
public class ClassLiveWatchController {

    private final ClassLiveWatchServiceImpl classLiveWatchService;

    private final StringRedisTemplate redisTemplate;

    private final RestFulTokenController restFulTokenController;
    /**
     * 显示谁看了什么课多长时间(直播的)
     */
    @SneakyThrows
    @GetMapping("/show/classlive/{id}")
    public ResponseResult<List<WatchUserTimeByLiveVo>> showClassLiveWatchPage(@PathVariable("id") Long id){
        List<WatchUserTimeByLiveVo> watchUserTimeByLiveVos = classLiveWatchService.listClassLiveWatchByUserid(id);
        ResponseResult<List<WatchUserTimeByLiveVo>> responseResult = null;
        if(watchUserTimeByLiveVos.size()>0){
            responseResult = new ResponseResult<>(200, "ok", watchUserTimeByLiveVos);
        }else{
            responseResult = new ResponseResult<>(6001, "no", null);
        }
        return responseResult;
    }

    /**
     * 进入直播间计时
     * @param userid
     * @param crlid
     * @return
     */
    @SneakyThrows
    @PostMapping("/add/watchlive")
    public ResponseEntity<String> whileInLiveWatch(@RequestParam("userid") Long userid,
                                                   @RequestParam("crlid") Long crlid,
                                                   @RequestParam("restFul") String addToken,
                                                   HttpServletRequest request){
        if (redisTemplate.opsForSet().remove(request.getHeader("x-username")+"-token",addToken)!=0) {
            Long id = 0l;
            //抛异常了,给重新存一个reids
            try {
                id=classLiveWatchService.insertClassLiveWatchWhileIn(userid, crlid);
            }catch(Exception e){
                ResponseEntity<Map<String, String>> mapResponseEntity = restFulTokenController.queryCouldAdd(request);
                String restFul = mapResponseEntity.getBody().get("restFul");
                return new ResponseEntity<String>(restFul, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(id > 0l){
                return new ResponseEntity<String>(id.toString(), HttpStatus.OK);
            }else if(id == 0l){
                return new ResponseEntity<String>("添加失败",HttpStatus.INTERNAL_SERVER_ERROR);
            }else if(id ==-2l){
                return new ResponseEntity<>("重复进入同一个直播间", HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<String>("该直播间暂未开启直播,不记录数据", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<String>("重复添加或伪造", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @SneakyThrows
    @PostMapping("/update/quitwatch")
    public ResponseEntity<Void> whileQuitLiveWatch(@RequestParam(defaultValue ="0l",required = false,value ="id") Long id){
        int i = classLiveWatchService.updateClassLiveWatchById(id);
        if(i>0){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
