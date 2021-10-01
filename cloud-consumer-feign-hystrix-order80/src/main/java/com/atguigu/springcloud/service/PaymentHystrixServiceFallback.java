package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceFallback implements PaymentHystrixService {

    @Override
    public String serviceOk(int id) {
        return "PaymentHystrixServiceFallback ------ serviceOk.";
    }

    @Override
    public String serviceTimeout(int id) {
        return "PaymentHystrixServiceFallback ------ serviceTimeout.";
    }
}
