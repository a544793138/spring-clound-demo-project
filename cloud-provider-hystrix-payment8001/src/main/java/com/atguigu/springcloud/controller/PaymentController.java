package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/service-ok/{id}")
    public String serviceOk(@PathVariable("id") int id) {
        String result = paymentService.serviceOk(id);
        log.info(result);
        return result;
    }

    @GetMapping("/payment/hystrix/service-timeout/{id}")
    public String serviceTimeout(@PathVariable("id") int id) {
        String result = paymentService.serviceTimeout(id);
        log.info(result);
        return result;
    }

}
