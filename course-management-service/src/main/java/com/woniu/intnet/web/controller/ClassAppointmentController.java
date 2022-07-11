package com.woniu.intnet.web.controller;


import com.woniu.service.impl.ClassAppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassAppointmentController {

    private final ClassAppointmentServiceImpl classAppointmentService;


    @SneakyThrows
    @PostMapping("/reserve/add")
    public ResponseEntity<String> addClassAppointment(@RequestParam(value = "userid",required = false,defaultValue = "0") Long userid,
                                                      @RequestParam(value = "crlid",required = false,defaultValue = "0") Long crlid){
        Long result = classAppointmentService.insertClassAppointment(userid, crlid);
        ResponseEntity<String> responseEntity = null;
        if(result==-1l){
            responseEntity =new ResponseEntity<>("直播不存在", HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(result==-2l){
            responseEntity = new ResponseEntity<>("该直播已被关注",HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(result == -3l){
            responseEntity = new ResponseEntity<>("该用户不存在", HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(result>0){
            responseEntity = new ResponseEntity<>(result.toString(), HttpStatus.OK);
        }
        return responseEntity;
    }

    @SneakyThrows
    @PostMapping("/reserve/delete")
    public ResponseEntity<String> deleteClassAppointment(@RequestParam(value = "userid",required = false,defaultValue = "0") Long userid,
                                                         @RequestParam(value = "crlid",required = false,defaultValue = "0") Long crlid){
        int result = classAppointmentService.deleteClassAppointmentById(userid, crlid);
        ResponseEntity<String> responseEntity = null;
        if(result==-1){
            responseEntity =new ResponseEntity<>("直播不存在", HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(result==-2){
            responseEntity = new ResponseEntity<>("该直播关注早已删除",HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(result == -3){
            responseEntity = new ResponseEntity<>("该用户不存在", HttpStatus.INTERNAL_SERVER_ERROR);
        }else if(result>0){
            responseEntity = new ResponseEntity<>(result+"", HttpStatus.OK);
        }
        return responseEntity;

    }

}
