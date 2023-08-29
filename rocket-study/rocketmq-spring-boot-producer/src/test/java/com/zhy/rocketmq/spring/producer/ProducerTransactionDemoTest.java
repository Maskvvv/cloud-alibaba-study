package com.zhy.rocketmq.spring.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhouhongyin
 * @since 2023/8/29 21:25
 */
@SpringBootTest
class ProducerTransactionDemoTest {

    @Autowired
    private ProducerTransactionDemo producerTransactionDemo;

    @Test
    void sendTransactionMessage() {
        String topic = "my-spring-boot-topic";
        String message = "hello spring-boot transaction rocketmq";

        producerTransactionDemo.sendTransactionMessage(topic, message);

    }
}
