package com.woniu.intnet.web.controller;


import com.woniu.intnet.web.vo.ShowPeopleViewVo;
import com.woniu.service.impl.ClassLiveWatchTotleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassLiveWatchTotleController {


    private final ClassLiveWatchTotleServiceImpl classLiveWatchTotleService;

    /**
     * 显示单个的直播有效人数和最大人数
     * @param crlid
     * @return
     */
    @SneakyThrows
    @GetMapping("/show/{crlid}/view")
    public ResponseEntity<ShowPeopleViewVo> getShowPeopleViewVoByCrlid(@PathVariable("crlid") Long crlid){
        ShowPeopleViewVo showPeopleViewByCrlid = classLiveWatchTotleService.getShowPeopleViewByCrlid(crlid);
        if(showPeopleViewByCrlid!=null){
            return new ResponseEntity<>(showPeopleViewByCrlid, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
