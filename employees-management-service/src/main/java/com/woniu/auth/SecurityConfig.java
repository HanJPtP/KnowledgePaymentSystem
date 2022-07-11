package com.woniu.auth;

import com.woniu.auth.filter.JwtFilter;
import com.woniu.handler.SimpleAccessDeniedHandler;
import com.woniu.handler.SimpleAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)// spring security 的注解功能默认是关闭的
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 这个 configure 配置方法中的各种配置的本质、底层就是在控制、调整过滤器链。
     *
     * http.formLogin()         影响到的就是 LoginFilter
     * http.sessionManagement() 影响到的就是 SessionStorageFilter
     http.csrf()                影响到的就是 CsrfFilter
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable(); // 本项目的 spring security 过滤器链上就没有登录有关的 Filter了。
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();  // 本项目的 spring security 过滤器链上就没有 csrfFilter

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated(); // 2

        //认证和鉴权异常配置
        http.exceptionHandling()
                .authenticationEntryPoint(new SimpleAuthenticationEntryPoint())//认证异常处理器,输入非登录请求，返回一个状态码
                .accessDeniedHandler(new SimpleAccessDeniedHandler());//鉴权异常处理器

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
