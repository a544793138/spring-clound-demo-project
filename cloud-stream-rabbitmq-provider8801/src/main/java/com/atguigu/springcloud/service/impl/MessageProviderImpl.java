package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 这个服务类是用来生产消息的，也就是消息的发送者
 */
// 这里不再使用使用 @Service 进行标注了，而是和 Stream 打交道，所以需要将这个服务类注册跟绑定器绑定，绑定到 Source 上，定义成消息的推送者身份
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements MessageProvider {

    // 这个是消息的发送管道，这里注意，名称一定是 output，这个是跟配置文件中 spring.cloud.stream.bindings.output 对应的
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String s = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(s).build());
        log.info("*****UUID: " + s);
        return s;
    }
}
