package com.zhy.cloud.producer.demos.rocketmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhouhongyin
 * @since 2023/8/29 22:15
 */
@SpringBootTest
class MyProducerTest {

    @Autowired
    private MyProducer myProducer;

    @Test
    void sendMessage() {

        myProducer.sendMessage("hello spring cloud message");

    }
}
