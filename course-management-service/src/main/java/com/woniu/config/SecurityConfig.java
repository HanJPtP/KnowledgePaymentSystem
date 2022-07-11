package com.woniu.config;




import com.woniu.filter.JwsTokenFilter;
import com.woniu.handler.SimpleAccessDeniedHandler;
import com.woniu.handler.SimpleAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启spring security注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwsTokenFilter jwsTokenFilter;
    /**
     * 使用spring security推荐的密码加密器,并加载到ioc容器中
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 重写过滤器链的
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置所需要拦截的请求
        http.authorizeRequests()
                .anyRequest().authenticated();//除了上述外,都需要登录才能拥有
        //http所使用的登录方式
        http.formLogin().disable();//这句配置很重要，新手容易忘记。放开 login.html和dologin 的访问权;

        //前后端项目中要禁用掉session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //添加自定义的token过滤器到User...Filter过滤器后面
        http.addFilterAfter(jwsTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //认证和鉴权异常配置
        http.exceptionHandling()
                .authenticationEntryPoint(new SimpleAuthenticationEntryPoint())//认证异常处理器,输入非登录请求，返回一个状态码
                .accessDeniedHandler(new SimpleAccessDeniedHandler());//鉴权异常处理器
        http.csrf().disable();

    }
}
