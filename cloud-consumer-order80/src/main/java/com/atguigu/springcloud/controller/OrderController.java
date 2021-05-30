package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

//    @Resource
//    private LoadBalance loadBalance;

//    @Resource
//    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
//        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class).getBody();
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }

    /**
     * 自定义负载均衡算法测试接口
     */
//    @GetMapping("/consumer/payment/lb")
//    public String getLb() {
//        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
//        if (instances == null || instances.size() <= 0) {
//            return null;
//        }
//        ServiceInstance serviceInstance = loadBalance.pickInstance(instances);
//        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/lb", String.class);
//    }
}
