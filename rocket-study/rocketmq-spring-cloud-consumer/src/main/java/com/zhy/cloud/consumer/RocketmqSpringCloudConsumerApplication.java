package com.zhy.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(Sink.class)
@SpringBootApplication
public class RocketmqSpringCloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqSpringCloudConsumerApplication.class, args);
    }

}
