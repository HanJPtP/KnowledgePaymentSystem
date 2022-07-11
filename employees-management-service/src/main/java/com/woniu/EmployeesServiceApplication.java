package com.woniu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.woniu.outlet.dao.mysql.mapper"})
public class EmployeesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesServiceApplication.class, args);
    }

}
