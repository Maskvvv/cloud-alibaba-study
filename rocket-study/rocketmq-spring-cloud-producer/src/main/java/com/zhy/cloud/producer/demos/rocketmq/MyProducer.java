package com.zhy.cloud.producer.demos.rocketmq;

import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author zhouhongyin
 * @since 2023/8/29 22:11
 */
@Component
public class MyProducer {


    @Resource
    private Source source;

    public void sendMessage(String msg) {

        HashMap<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, "TagA");
        MessageHeaders messageHeaders = new MessageHeaders(headers);
        Message<String> message = MessageBuilder.createMessage(msg, messageHeaders);

        source.output().send(message);
    }

}
