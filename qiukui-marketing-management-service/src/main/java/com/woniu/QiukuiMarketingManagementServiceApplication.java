package com.woniu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@MapperScan(basePackages = {"com.woniu.outlet.dao.mysql"})
@EnableScheduling  //开启定时任务
@EnableFeignClients(basePackages = {"com.woniu.outlet.client"})
public class QiukuiMarketingManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QiukuiMarketingManagementServiceApplication.class, args);
    }

}
