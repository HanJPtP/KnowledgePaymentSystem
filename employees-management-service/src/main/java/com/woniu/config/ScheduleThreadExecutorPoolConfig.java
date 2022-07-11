package com.woniu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Component
public class ScheduleThreadExecutorPoolConfig {

    @Bean
    public ScheduledThreadPoolExecutor getScheduleThreadExecutor() {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        return executor;
    }

}
