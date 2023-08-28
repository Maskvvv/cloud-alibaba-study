package com.zhy.rocketmq.client.broadcast;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * <p>广播消息生产者</p>
 *
 * @author zhouhongyin
 * @since 2023/8/27 19:49
 */
public class BroadCastProducer {

    public static void main(String[] args) throws MQClientException {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("default_producer_group");
            producer.setNamesrvAddr("localhost:9876");
            producer.start();

            String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};

            for (int i = 0; i < 10; i++) {
                int orderId = i % 10;
                Message msg = new Message("BroadCastTopicTest", tags[i % tags.length], "KEY" + i,
                                ("orderId " + orderId +" i " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                SendResult sendResult = producer.send(msg, new SelectMessageQueueByHash(), orderId);

                System.out.printf("%s%n", sendResult);
            }

            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MQClientException(e.getMessage(), null);
        }
    }
}
