package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PaymentService {

    public String serviceOk(int id) {
        return String.format("serviceOk: %s, threadName: %s", id, Thread.currentThread().getName());
    }

    @HystrixCommand(fallbackMethod = "serviceTimeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String serviceTimeout(int id) {
        final int sleepTime = 1;
        try {
            Thread.sleep(sleepTime * 1000);
        } catch (Exception e) {
            log.error("Sleep InterruptedException", e);
        }
        return String.format("serviceTimeout: %s, threadName: %s", id, Thread.currentThread().getName());
    }

    public String serviceTimeoutFallback(int id) {
        return String.format("serviceTimeoutFallback :%s. Wait timeout.", id);
    }

    @HystrixCommand(fallbackMethod = "serviceCircuitBreakerFallback", commandProperties = {
            // 是否启用断路器机制
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 设置在滚动时间窗口期内，断路器发送熔断的最小请求次数。
            // 只有在滚动时间窗口期内，且达到这个请求次数后，才开始判断这些请求里的失败率是否达到设置的值，如果达到才开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 休眠时间窗口期。表示断路器开启后，经过这个时间后，断路器会切换到“半开”状体，尝试放行请求命令，如果依旧失败，断路器就切换回开启状态，如果成功，则关闭断路器，恢复调用
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 设置滚动时间窗口期内，一定请求次数下的最高失败率。达到这个失败率后，就会发送熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
            // 设置滚动时间窗口期
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    })
    public String serviceCircuitBreaker(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("id 不能为复数");
        }
        return String.format("serviceCircuitBreaker: %s, threadName: %s", id, Thread.currentThread().getName());
    }

    public String serviceCircuitBreakerFallback(int id, Exception e) {
        String message = "";
        if (Objects.nonNull(e)) {
            message = e.getMessage();
        }
        return String.format("serviceCircuitBreakerFallback ：exception message: %s, id: %s.", message, id);
    }

}
