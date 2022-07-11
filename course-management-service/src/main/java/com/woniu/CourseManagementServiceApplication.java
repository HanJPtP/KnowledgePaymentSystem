package com.woniu;

import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan(basePackages = {"com.woniu.outnet.dao.mysql"})
@EnableScheduling
@EnableAsync
@EnableElasticsearchRepositories(basePackages = {"com.woniu.outnet.dao.elasticserch"})
@EnableFeignClients(basePackages = {"com.woniu.intnet.web.client"})
@EnableRedisRepositories(basePackages = {"com.woniu.outnet.dao.redis"})
public class CourseManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseManagementServiceApplication.class, args);
    }

    @Bean
    public SnowFlakeGenerator snowFlakeGenerator(){
        return  new SnowFlakeGenerator();
    }

    @PostConstruct
    void started() {
        //时区设置：中国上海
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
