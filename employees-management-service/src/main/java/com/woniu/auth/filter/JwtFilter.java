package com.woniu.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //  step 1. 从请求头的 x-jwt-token 中取一个 x-username 。
        String username = request.getHeader("x-username");
        log.debug("收到请求 {}，其头部携带 x-username：{}", request.getRequestURI(), username);

        if (!StringUtils.hasText(username)) {
            // jwt-filter 没有向上下文中放 authentication-token ，那么自然就是由 jwt-filter 后面的 filter 向上下文中存放 authentication-token
            // 极端情况下，如果没有别的 filter 放，那么最后 anonymouse-filter 它会放一个 authentication-token，代表当前用户使用的是匿名账号。
            filterChain.doFilter(request, response);
            return;
        }

        // 前提条件：auth-service 当初在处理登录逻辑时，在用户认证成功后，需要将他的所有权限存入 redis 。
        String commaSeparatedString = (String) redisTemplate.opsForHash().get("user_roles", "user:" + username);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedString);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);

        //  5. authentication-token 存入上下文。
        // 这样，JwtFilter 后面的 filter 就能发现上下文中有 authentication-token ，所以它们也就不会再向上下文中放 authentication-token 了。
        SecurityContextHolder.getContext().setAuthentication(token);

        // 放行请求，让『最后的SecurityFilterInterceptor』触发执行：从公共区取出 Authentication Token 作鉴权操作。
        filterChain.doFilter(request, response);

    }
}
