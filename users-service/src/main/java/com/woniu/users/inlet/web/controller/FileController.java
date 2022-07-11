package com.woniu.users.inlet.web.controller;

import com.woniu.knowledgepayment.commons.util.MinioUtils;
import com.woniu.users.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

/**
 * 文件上传下载
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final MinioUtils minioUtils;

    /**
     *  上传文件
     * @param files
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult<Object> upload(@RequestPart("files") MultipartFile[] files){
        String str = "";
        for(MultipartFile file : files){
            log.info("{}",file.getOriginalFilename());
            minioUtils.putObject("user-service", file, file.getOriginalFilename(),file.getContentType());
            String filecontent = minioUtils.getObjectUrl("user-service", file.getOriginalFilename(), 7, TimeUnit.DAYS);
            // 将生成的下载链接存入数据库
                str = filecontent;
        }

        ResponseResult<Object> responseResult = null;
        if (str.length()>0){
            responseResult = new ResponseResult<>(200,"上传成功", str);
        }else{
            responseResult = new ResponseResult<>(500, "上传文件失败!");
        }
        return responseResult;
    }
}
