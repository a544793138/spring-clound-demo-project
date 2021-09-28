package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
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
    public String serviceTimeout(@PathVariable("id") int id) {
        return paymentHystrixService.serviceTimeout(id);
    }

}
