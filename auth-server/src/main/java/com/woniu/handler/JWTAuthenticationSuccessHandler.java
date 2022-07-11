package com.woniu.handler;

import com.woniu.util.JsonUtils;
import com.woniu.util.JwtUtils;
import com.woniu.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
@Component
public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {

        // authentication 对象携带了当前登陆用户名等相关信息
        User user = (User) authentication.getPrincipal();

        resp.setContentType("application/json;charset=UTF-8");
        String jwtStr = JwtUtils.createJWT(user.getUsername());

        Map<Object, Object> map = MapUtils.builder()
                .put("code", 10086)
                .put("msg", "success")
                .put("jwt-token", jwtStr)
                .build();

        String jsonStr = JsonUtils.toJSONString(map);

        String authrities = StringUtils.collectionToCommaDelimitedString(user.getAuthorities());

        log.debug("用户 {} 所具有的权限：{}", user.getUsername(), authrities);

        redisTemplate.opsForHash().put("user_roles", "user:" + user.getUsername(), authrities);

        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        log.info("{}", jsonStr);
        out.write(jsonStr);
        out.flush();
        out.close();
    }
}

