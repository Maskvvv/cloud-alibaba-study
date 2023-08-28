package com.zhy.rocketmq.client.schedule;

import java.nio.charset.StandardCharsets;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * <p> 延时消息生产者 </p>
 *
 * TimerMessage is a new feature in version 5.0, so be sure to upgrade RocketMQ to version 5.0+ before using it.
 *
 * @author zhouhongyin
 * @since 2023/8/27 20:58
 */
public class TimerMessageProducer {


    public static final String PRODUCER_GROUP = "TimerMessageProducerGroup";
    public static final String DEFAULT_NAMESRVADDR = "127.0.0.1:9876";
    public static final String TOPIC = "TimerTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);

        producer.setNamesrvAddr(DEFAULT_NAMESRVADDR);

        // Launch producer
        producer.start();
        int totalMessagesToSend = 10;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message(TOPIC, ("Hello scheduled message " + i).getBytes(StandardCharsets.UTF_8));
            // This message will be delivered to consumer 10 seconds later.
            //message.setDelayTimeSec(10);
            // The effect is the same as the above
            // message.setDelayTimeMs(10_000L);
            // Set the specific delivery time, and the effect is the same as the above
            message.setDeliverTimeMs(System.currentTimeMillis() + 12_000L);
            // Send the message
            SendResult result = producer.send(message);
            System.out.printf(result + "\n");
        }

        // Shutdown producer after use.
        producer.shutdown();
    }
}
