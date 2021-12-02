package com.atguigu.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Into MyLogGatewayFilter.");
        final String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (StringUtils.isBlank(username)) {
            log.error("Unable to parse MyLogGatewayFilter because parameter `username` must not be null or empty.");
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response.writeWith(Mono.just(new DefaultDataBufferFactory().wrap("Error".getBytes(StandardCharsets.UTF_8))));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
