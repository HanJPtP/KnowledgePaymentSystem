package com.woniu.config;

import com.woniu.handler.JWTAuthenticationSuccessHandler;
import com.woniu.handler.SimpleAccessDeniedHandler;
import com.woniu.handler.SimpleAuthenticationEntryPoint;
import com.woniu.handler.SimpleFailureAuthenticationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JWTAuthenticationSuccessHandler successHandler;

    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(successHandler)
                .failureHandler(new SimpleFailureAuthenticationHandler());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated(); // 2
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
