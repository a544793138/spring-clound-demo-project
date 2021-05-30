package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义的负载均衡接口
 */
public interface LoadBalance {

    /**
     * 初始化负载均衡时，告诉负载均衡，需要负载的服务，在注册中心有多少个实例
     *
     * @param serviceInstances 需要负载的服务，在注冊中心上拥有的实例列表
     * @return 挑选好的服务实例
     */
    ServiceInstance pickInstance(List<ServiceInstance> serviceInstances);


}
