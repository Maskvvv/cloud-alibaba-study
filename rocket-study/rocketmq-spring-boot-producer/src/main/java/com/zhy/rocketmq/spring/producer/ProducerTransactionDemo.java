package com.zhy.rocketmq.spring.producer;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * <p>事务消息</p>
 *
 * @author zhouhongyin
 * @since 2023/8/28 21:17
 */
@Component
public class ProducerTransactionDemo {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendTransactionMessage(String topic, String message) {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {

                Message<String> msg = MessageBuilder.withPayload(message + i).
                        setHeader(RocketMQHeaders.TRANSACTION_ID, "KEY_" + i).build();

                String destination = topic + ":" + tags[i % tags.length];
                SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, msg, destination);

                System.out.printf("------rocketMQTemplate send Transactional msg body = %s , sendResult=%s %n",
                        msg.getPayload(), sendResult.getSendStatus());

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
