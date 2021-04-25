package com.atguigu.ribbon.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRibbonConfig {

    @Bean
    public IRule iRule() {
        // 将负载均衡算法从默认的轮询改为随机轮询
        return new RandomRule();
    }
}
