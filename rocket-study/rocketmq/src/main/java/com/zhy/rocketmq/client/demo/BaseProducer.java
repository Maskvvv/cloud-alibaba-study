package com.zhy.rocketmq.client.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * <p>基础环境生产者实现</p>
 *
 * @author zhouhongyin
 * @since 2023/8/23 21:24
 */
public class BaseProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-producer-group1");
        producer.setNamesrvAddr("192.168.0.133:9876");
        producer.setSendMsgTimeout(30000); // 设置超时时间为30秒
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message("myTopic1", "tagb", ("hello rocketmq" + i).getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }

        producer.shutdown();
    }



}
