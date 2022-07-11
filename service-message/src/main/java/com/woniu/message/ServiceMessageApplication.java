package com.woniu.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ServiceMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMessageApplication.class, args);
    }

}
