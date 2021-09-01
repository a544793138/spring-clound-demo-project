package com.atguigu.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public String serviceOk(int id) {
        return String.format("serviceOk: %s, threadName: %s", id, Thread.currentThread().getName());
    }

    public String serviceTimeout(int id) {
        final int sleepTime = 3;
        try {
            Thread.sleep(sleepTime * 1000);
        } catch (Exception e) {
            log.error("Sleep InterruptedException", e);
        }
        return String.format("serviceTimeout: %s, threadName: %s", id, Thread.currentThread().getName());
    }
}
