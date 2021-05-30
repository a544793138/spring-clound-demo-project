package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义的负载均衡算法类
 *
 * 使用时需要标注 @Component 注解
 */
//@Component
public class MyLoadBalance implements LoadBalance{

    private AtomicInteger index = new AtomicInteger(0);

    @Override
    public ServiceInstance pickInstance(List<ServiceInstance> serviceInstances) {
        int pickedIndex = pickIndex(serviceInstances.size());
        return serviceInstances.get(pickedIndex);
    }

    public final int pickIndex(int instanceSiz) {
        for (;;) {
            int current = index.get();
            int next = (current + 1) % instanceSiz;
            if (index.compareAndSet(current, next)) {
                return next;
            }
        }
    }

}
