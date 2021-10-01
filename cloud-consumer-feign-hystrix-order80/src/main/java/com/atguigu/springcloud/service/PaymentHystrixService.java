package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = PaymentHystrixServiceFallback.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/service-ok/{id}")
    String serviceOk(@PathVariable("id") int id);

    @GetMapping("/payment/hystrix/service-timeout/{id}")
    String serviceTimeout(@PathVariable("id") int id);
}
