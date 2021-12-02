package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功，serverPort: " + serverPort, payment);
        } else {
            return new CommonResult<>(400, "插入数据库失败", null);
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment);
        if (Objects.nonNull(payment)) {
            return new CommonResult<>(200, "查询数据库成功，serverPort: " + serverPort, payment);
        } else {
            return new CommonResult<>(400, "没有对应记录，查询 ID ：" + id, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(service -> log.info("service: {}", service));

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        instances.forEach(instance -> log.info("instanceId={}, host={}, port={}, uri={}, scheme={}, metadata={}",
                instance.getInstanceId(), instance.getHost(), instance.getPort(), instance.getUri(), instance.getScheme(), instance.getMetadata()));

        return discoveryClient;
    }

    /**
     * 自定义负载均衡算法测试接口
     */
    @GetMapping("/payment/lb")
    public String myLoadBalance() {
        return serverPort;
    }
}
