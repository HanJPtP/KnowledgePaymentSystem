package com.woniu.filter;

import com.woniu.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GatewayFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // step 1. 从请求头的 x-jwt-token 中取一个 jwt-token 串。
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String jwtStr = headers.getFirst("x-jwt-token");
//        headers.set("x-username", "");

        if (!StringUtils.hasText(jwtStr)) {
            log.info("头部 jwt-token 为空");
            return chain.filter(exchange);
        }

        //  step 2. 校验 jwt-token 的合法性。
        if (!JwtUtils.verify(jwtStr)) {
            log.info("验证 jwt-token 失败");
            return chain.filter(exchange);
        }

        // step 3. 从 jwtstr 中获得 username
        String username = JwtUtils.getUsernameFromJWT(jwtStr);

        exchange.getRequest().mutate().header("x-username", username);

        return chain.filter(exchange);
    }
}
