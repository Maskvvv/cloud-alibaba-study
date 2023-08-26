package com.zhy.rocketmq.async;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 生产者发送异步消息 </p>
 *
 * @author zhouhongyin
 * @since 2023/8/26 22:49
 */
public class AsyncProducerExample {
    private static final Logger log = LoggerFactory.getLogger(AsyncProducerExample.class);

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my-producer-group1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);


        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Message message = new Message("myTopic1", "tagb", ("hello rocketmq" + i).getBytes(StandardCharsets.UTF_8));
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                    countDownLatch.countDown();
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println(e.getMessage());
                    countDownLatch.countDown();
                }
            });
        }

        System.out.println("main thread");
        countDownLatch.await();
        producer.shutdown();
    }


}
