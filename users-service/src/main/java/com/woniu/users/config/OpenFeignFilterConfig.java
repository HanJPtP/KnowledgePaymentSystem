package com.woniu.users.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
public class OpenFeignFilterConfig implements RequestInterceptor {
    @Value("x-username")
    private String headerName;
    @Override
    public void apply(RequestTemplate requestTemplate) {

        //获得所有请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        /**
         * 非转发的请求调用openFeign,会导致ServletRequestAttributes得到的值是空
         */
        if (attributes == null){
            requestTemplate.header("x-username", "admin");
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();


        /**
         * 下面太长,全部存会报错
         * 请求头的key:user-agent
         * 请求头的values:vscode-restclient
         * 请求头的key:content-type
         * 请求头的values:application/json
         * 请求头的key:rememberme
         * 请求头的values:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZUtleSI6ImFkbWluIn0.LoVsVWzr5CJ2QwYtCTHpYFx-FkZmuiPKlCzanKs-BpM
         * 请求头的key:accept-encoding
         * 请求头的values:gzip, deflate
         * 请求头的key:content-length
         * 请求头的values:32
         * 请求头的key:cookie
         * 请求头的values:JSESSIONID=3CE0782CA62B7585AC0C4592EF03D899
         * 请求头的key:host
         * 请求头的values:127.0.0.1:8080
         * 请求头的key:connection
         * 请求头的values:close
         */
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String s = headerNames.nextElement();
                if (s.equals(headerName)) {
                    System.out.println("请求头的key:" + s);
                    String header = request.getHeader(s);
                    System.out.println("请求头的values:" + header);
                    requestTemplate.header(s, header);
                }
            }
        }
    }
}
