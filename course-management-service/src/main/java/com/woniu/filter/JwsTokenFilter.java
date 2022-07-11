package com.woniu.filter;



import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwsTokenFilter extends OncePerRequestFilter {
    /**
     * 要想使用依赖注入,必须在ioc实例化,所有这个对象必须加载到ioc容器中
     */
//    private  final UsersAndPermsRedisDao usersAndPermsRedisDao;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //判断是不是登录请求,是登录请求,放行(避免没有token还要添加token)
        //获得当前的UsernamePasswordAuthenticationToken
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //当authentication不为null时,是登录请求,放行
        if(authentication!=null){
            filterChain.doFilter(request,response);
            return;
        }
        //验证token的正确性
        //token存在header中
        //从请求中获得header的键值对
        String tokenStr = request.getHeader("x-username");
        System.out.println(tokenStr);
//        //获得请求中的token,验证token是否为null
//        if(StringUtils.isEmpty(tokenStr)){
//            //请求中没有token，不用管
//            filterChain.doFilter(request,response);
//            return;
//        }
//        //验证tokenStr是否符合要求
//        if(!TokenUtil.verifyToken(tokenStr)){
//            //表示token不符合验证规则,需要放行
//            filterChain.doFilter(request,response);
//            return;
//        }
//        //现在tokenStr符合验证要求
//        //从中获的username对象
//        String userName = TokenUtil.getUserName(tokenStr);

//        //获得存储信息的容器
//        UsersAndPerms usersAndPerms = usersAndPermsRedisDao.findById(tokenStr).get();
//        System.out.println(usersAndPerms.getPermsList().toString());
//        String userAutherStr = String.join(",",usersAndPerms.getPermsList());
//        UserDetails userDetails = new User(usersAndPerms.getAccount(),
//                usersAndPerms.getUsers().getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(userAutherStr)
//                );
        //将两个token融合,(UsernamePasswordAuthenticationToken和JWSToken)

        //通过redis取权限
        Object authrities =redisTemplate.opsForHash().get("user_roles", "user:" + tokenStr);

        UserDetails userDetails = new User(tokenStr, "", AuthorityUtils.commaSeparatedStringToAuthorityList((String) authrities));

        //将jwstoken转为security的token
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        //添加上下文容器中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
