package com.woniu.intnet.web.controller;


import com.woniu.knowledgepayment.commons.util.MinioUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class FileUploadController {


    private final MinioUtils minioUtils;

    /**
     * 上传图片得到地址
     * @param files
     * @return
     */
    @SneakyThrows
    @PostMapping("/img/upload")
    public ResponseEntity<String> addImgToMinio(@RequestPart("files") MultipartFile[] files){
        //取出首位的数据
        MultipartFile file = files[0];
        //得到文件名
        String originalFilename = file.getOriginalFilename();
        //取得文件名后缀
        String fileSuffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //判断后缀
        if((!fileSuffixName.equals(".img"))&&(!fileSuffixName.equals(".png"))&&(!fileSuffixName.equals(".jpg"))){
            return new ResponseEntity<>("上传格式错误", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        minioUtils.putObject("class", file, file.getOriginalFilename(), file.getContentType());
        //取出路径
        String aClass = minioUtils.getObjectUrl("class", file.getOriginalFilename(), 7, TimeUnit.DAYS);
        //传回页面
        return new ResponseEntity<>(aClass, HttpStatus.OK);
    }

    /**
     * 上传文件获得地址
     * @param files
     * @return
     */
    @SneakyThrows
    @PostMapping("/files/upload")
    public ResponseEntity<String> addFileInMinio(@RequestPart("files") MultipartFile[] files){
        //取出首位的数据
        MultipartFile file = files[0];
        //得到文件名
        String originalFilename = file.getOriginalFilename();
        //上传文件
        minioUtils.putObject("class", file, file.getOriginalFilename(), file.getContentType());
        //取出路径
        String aClass = minioUtils.getObjectUrl("class", file.getOriginalFilename(), 7, TimeUnit.DAYS);
        //传回页面
        return new ResponseEntity<>(aClass, HttpStatus.OK);
    }
}
