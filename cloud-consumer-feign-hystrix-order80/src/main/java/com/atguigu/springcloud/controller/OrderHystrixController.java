package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/service-ok/{id}")
    public String serviceOk(@PathVariable("id") int id) {
        return paymentHystrixService.serviceOk(id);
    }

    @GetMapping("/consumer/payment/hystrix/service-timeout/{id}")
    @HystrixCommand(fallbackMethod = "serviceTimeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String serviceTimeout(@PathVariable("id") int id) {
        return paymentHystrixService.serviceTimeout(id);
    }

    public String serviceTimeoutFallback(@PathVariable("id") int id) {
        return String.format("consumer serviceTimeoutFallback :%s. Wait timeout.", id);
    }
}
