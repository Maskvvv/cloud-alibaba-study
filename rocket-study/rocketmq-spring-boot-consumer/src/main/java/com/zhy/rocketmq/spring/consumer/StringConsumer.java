package com.zhy.rocketmq.spring.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * StringConsumer
 */
@Service
@RocketMQMessageListener(
        topic = "my-spring-boot-topic",
        consumerGroup = "string_consumer_group1",
        //selectorExpression = "tagA",
        tlsEnable = "false")
public class StringConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.printf("------- StringConsumer received: %s \n", message);
    }
}
