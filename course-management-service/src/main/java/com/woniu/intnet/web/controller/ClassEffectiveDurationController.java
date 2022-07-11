package com.woniu.intnet.web.controller;


import com.woniu.service.impl.ClassEffectiveDurationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassEffectiveDurationController {

    private final ClassEffectiveDurationServiceImpl classEffectiveDurationService;

    /**
     * 修改时间
     * @param time
     * @return
     */
    @SneakyThrows
    @PostMapping("/time/update")
    public ResponseEntity<Void> updateTime(@RequestParam(value = "time",required = false,defaultValue = "50000") Long time){
        int i = classEffectiveDurationService.updateEffectiveDurationById(time);
        if(i>0){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
