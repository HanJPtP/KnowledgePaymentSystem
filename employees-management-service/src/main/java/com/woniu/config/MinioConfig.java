package com.woniu.config;


import com.woniu.knowledgepayment.commons.util.MinioUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MinioConfig {
//
//    @Bean
//    public MinioUtils minioUtils() {
//        MinioUtils minioUtils = new MinioUtils("http://192.172.0.205:9000", "minioadmin", "minioadmin");
//        // xxx 桶不存在，就创建它。未来，我们要向这个桶里放文件。
//        if (!minioUtils.doesBucketExists("goods")) {
//            minioUtils.createBucket("goods");
//        }
//        return minioUtils;
//    }
}
