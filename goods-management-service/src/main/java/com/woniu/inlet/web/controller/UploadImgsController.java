package com.woniu.inlet.web.controller;

import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import com.woniu.knowledgepayment.commons.util.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class UploadImgsController {

    @Autowired
    private MinioUtils minioUtils;

    @PostMapping("/img/upload")
    public ResponseResult uploadImg(@RequestPart("files") MultipartFile[] files) {
        String filename = null;
        for (MultipartFile file : files) {
            minioUtils.putObject("goods", file, file.getOriginalFilename(), file.getContentType());
            filename = file.getOriginalFilename();
            log.info("{}: {}", file.getOriginalFilename(), minioUtils.getObjectUrl("goods", file.getOriginalFilename(), 1, TimeUnit.DAYS));
        }
        ResponseResult responseResult;
        if (minioUtils.doesObjectExist("goods", filename))
            responseResult = new ResponseResult(200, "ok", minioUtils.getObjectUrl("goods", filename, 7, TimeUnit.DAYS));
        else
            responseResult = new ResponseResult(500, "上传失败", null);
        return responseResult;
    }

}
