package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import com.atguigu.springcloud.dao.PaymentDao;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * @Resource - 也可以用来做注入，它与 @Autowired 的区别是一个是 Java 的，另一个是 spring 的
     */
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
