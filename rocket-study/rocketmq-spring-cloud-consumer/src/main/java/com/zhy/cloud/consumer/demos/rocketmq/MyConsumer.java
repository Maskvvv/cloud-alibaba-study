package com.zhy.cloud.consumer.demos.rocketmq;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2023/8/29 22:26
 */
@Component
public class MyConsumer {

    @StreamListener(Sink.INPUT)
    public void processMessage(String message) {
        System.out.println("收到消息：" + message);
    }
}
