package com.zhy.rocketmq.client.schedule;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * <p> 延时消息消费者 </p>
 *
 * @author zhouhongyin
 * @since 2023/8/27 20:57
 */
public class ScheduledMessageConsumer {

    public static final String CONSUMER_GROUP = "ExampleConsumer";
    public static final String DEFAULT_NAMESRVADDR = "127.0.0.1:9876";
    public static final String TOPIC = "TestTopic";

    public static void main(String[] args) throws Exception {
        // Instantiate message consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);

        // Uncomment the following line while debugging, namesrvAddr should be set to your local address
        consumer.setNamesrvAddr(DEFAULT_NAMESRVADDR);

        // Subscribe topics
        consumer.subscribe(TOPIC, "*");
        // Register message listener
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            for (MessageExt message : messages) {
                // Print approximate delay time period
                System.out.printf("Receive message[msgId=%s %d  ms later]\n", message.getMsgId(),
                    System.currentTimeMillis() - message.getStoreTimestamp());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // Launch consumer
        consumer.start();
        //info:to see the time effect, run the consumer first , it will wait for the msg
        //then start the producer
    }
}
