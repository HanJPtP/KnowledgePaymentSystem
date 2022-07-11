package com.woniu.users.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
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
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 判断在“我”之前有没有 Filter 向上下文中存放 AuthenticationToken 。如果现在上下文中有，“我”不做任何实际操作，直接放行，让请求继续。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.info("Security Context 中已有 Authentication Token");
            filterChain.doFilter(request, response);
            return;

        }

        // 走到这里，“我”之前的 filter 还没有谁向上下文中存入 AuthenticationToken，那么，逻辑上，就应该由我来存入。
        // “我”的存 Authentication Token 的逻辑是：
        //  1. 从请求头的 x-jwt-token 中取一个 jwt-token 串。
        //  2. 校验 jwt-token 的合法性。
        //  3. 从 jwt-token 的 payload 中取到用户名 username 。
        //     前提条件：auth-service 当初生成 jwt-token 时 就应该向 jwt-token 的payload 中存入登陆用户的 username 。
        //  4. 生成 authentication token，在这个环节，需要从 redis 中取当前用户的所具有的权限列表。
        //     前提条件：auth-service 当初在处理登录逻辑时，在用户认证成功后，需要将他的所有权限存入 redis 。
        //  5. authentication-token 存入上下文。

        //========1)从请求头的x-jwt-token得到一个jwt-json串=======
        String username = request.getHeader("x-username");

        log.info("收到请求 {}，其头部携带 jwt-token：{}", request.getRequestURI(), username);

        //判断jwtStr是否有值
//        if(!StringUtils.hasText(jwtStr)){
//            //没有值，放行
//            filterChain.doFilter(request, response);
//            return;
//        }

        //=======2)校验jwt-token的合法性===============
//        if(!JwtUtils.verify(jwtStr)){
//            //不合法，放行
//            filterChain.doFilter(request, response);
//            return;
//        }

        //========3)从jwt-json字符串拿到username
//        String username = JwtUtils.getUsernameFromJWT(jwtStr);

        //=======4)生成authentication token
        String authorities = (String)stringRedisTemplate.opsForHash().get("user_roles", "user:" + username);
        log.info("{}所具有的权限：{}", username,authorities);

        UserDetails userDetails = new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
        //将权限转成集合
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(), userDetails.getAuthorities());


        //==========5)将token存入上下文
        SecurityContextHolder.getContext().setAuthentication(token);

        //放行
        filterChain.doFilter(request, response);

    }
}
