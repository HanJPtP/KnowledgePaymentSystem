package com.woniu.intnet.web.controller;


import com.woniu.intnet.web.vo.EmailConfigInfoVo;
import com.woniu.service.impl.ClassEmailConfigureInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassEmailConfigureInfoController {

    private final ClassEmailConfigureInfoServiceImpl classEmailConfigureInfoService;

    @SneakyThrows
    @PostMapping("/email/update")
    public ResponseEntity<String> UpdateEmailToOther(@RequestBody EmailConfigInfoVo emailConfigInfoVo){
        int i = classEmailConfigureInfoService.updateClassEmailConfigureInfoById(emailConfigInfoVo);
        if(i>0){
            return new ResponseEntity<String>("修改成功",HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("修改失败",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
