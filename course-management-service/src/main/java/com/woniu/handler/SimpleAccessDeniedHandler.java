package com.woniu.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.util.ResponseResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 鉴权异常处理器
 */
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ResponseResult<Void> responseResult =new ResponseResult<>(405, "没有权限");
        //设置编码集

        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = objectMapper.writeValueAsString(responseResult);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}
