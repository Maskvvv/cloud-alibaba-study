package com.zhy.rocketmq.spring.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhouhongyin
 * @since 2023/8/28 21:19
 */
@SpringBootTest
public class ProducerDemoTest {

    @Autowired
    private ProducerDemo producerDemo;

    @Test
    void sendMessage() {
        String topic = "my-spring-boot-topic";
        String message = "hello spring-boot rocketmq";

        producerDemo.sendMessage(topic, message);
    }

}
