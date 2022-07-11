package com.woniu;

import com.woniu.knowledgepayment.commons.util.SnowFlakeGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.woniu.outlet.mysql.mapper"})
@EnableFeignClients(basePackages = {"com.woniu.inlet.web.client"})
@EnableElasticsearchRepositories(basePackages = {"com.woniu.outlet.dao.repository"})
public class GoodsManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsManagementServiceApplication.class, args);
    }

    @Bean
    public SnowFlakeGenerator getSnowFlakeGenerator() {
        return new SnowFlakeGenerator(10, 15);
    }

    @Bean
    public ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        ScheduledThreadPoolExecutor executor= (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        return executor;
    }

}
