package com.woniu.users.auth.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
@Slf4j
public class RequestHeaderConfig {


    @Bean
    public RequestInterceptor requestInterceptor() {

        //获得请求头，改变请求头键值为x-username
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if(attributes == null){
                return;
            }

            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            log.info("-1-1-1" + headerNames);
            if (headerNames == null)
                return;
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                log.info(name);
                if(name.equals("x-username")){
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                    log.info(name + "--------------==+");
                    break;
                }

            }
        };
    }
}