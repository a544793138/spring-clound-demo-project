package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("payment_routh", predicateSpec -> predicateSpec.path("payment/get/**").uri("http://localhost:8001/payment/get/**"))
                .route("payment_routh2", predicateSpec -> predicateSpec.path("payment/lb/**").uri("http://localhost:8001/payment/lb/**"))
                .build();
    }
}
