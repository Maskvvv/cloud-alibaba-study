package com.zhy.rocketmq.schedule;

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
public class TimerMessageConsumer {

    public static final String CONSUMER_GROUP = "TimerMessageConsumerGroup";
    public static final String DEFAULT_NAMESRVADDR = "127.0.0.1:9876";
    public static final String TOPIC = "TimerTopic";

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);

        consumer.setNamesrvAddr(DEFAULT_NAMESRVADDR);

        consumer.subscribe(TOPIC, "*");

        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            for (MessageExt message : messages) {
                System.out.printf("Receive message[msgId=%s %d  ms later]\n", message.getMsgId(),
                    System.currentTimeMillis() - message.getBornTimestamp());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
