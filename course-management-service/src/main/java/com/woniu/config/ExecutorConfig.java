package com.woniu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ExecutorConfig {
    /**
     * 设置ThreadPoolExecutor的核心池大小
     */
    private final int corePoolSize = 10;
    /**
     * 设置ThreadPoolExecutor的最大池大小
     */
    private final int maxPoolSize = 200;
    /**
     * 设置ThreadPoolExecutor的blockingQueue
     */
    private final int queueCapacity = 10;
    /**
     * 给延迟任务删除redis中存在的多余的键值对
     */



    @Bean("AsyncFrist")
    public Executor AsyncFrist(){
        //线程池任务执行器 创建一个线程池(执行)
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //ThreadPoolTaskScheduler(用于调度)
        //设置核心池大小
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        //设置队列容量
        executor.setQueueCapacity(queueCapacity);
        //设置线程前缀
        executor.setThreadNamePrefix("定时发送邮件线程-");
        executor.initialize();
        return executor;
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        return Executors.newScheduledThreadPool(10);
    }
}
