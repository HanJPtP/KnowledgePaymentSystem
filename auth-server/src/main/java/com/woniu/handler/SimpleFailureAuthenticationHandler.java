package com.woniu.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.knowledgepayment.commons.entity.ResponseResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleFailureAuthenticationHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResponseResult<Object> responseResultVo = null;
        if (e instanceof InternalAuthenticationServiceException) {
            responseResultVo = new ResponseResult<>(2001, "账号不存在");
        } else if ( e instanceof BadCredentialsException) {
            responseResultVo = new ResponseResult<>(2002, "账号或者密码错误, 请重新输入");
        } else if ( e instanceof LockedException) {
            responseResultVo = new ResponseResult<>(2003, "账号锁定, 请联系管理员");
        }
        String s = mapper.writeValueAsString(responseResultVo);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.flush();
        writer.close();
    }
}
