package com.zhy.rocketmq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> 批量消息生产者 </p>
 *
 * @author zhouhongyin
 * @since 2023/8/27 21:09
 */
public class SplitBatchProducer {

    public static final String PRODUCER_GROUP = "BatchProducerGroupName";
    public static final String DEFAULT_NAMESRVADDR = "127.0.0.1:9876";

    public static final int MESSAGE_COUNT = 100 * 1000;
    public static final String TOPIC = "BatchTest";
    public static final String TAG = "Tag";

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
        // Uncomment the following line while debugging, namesrvAddr should be set to your local address
//        producer.setNamesrvAddr(DEFAULT_NAMESRVADDR);
        producer.start();

        //large batch
        List<Message> messages = new ArrayList<>(MESSAGE_COUNT);
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            messages.add(new Message(TOPIC, TAG, "OrderID" + i, ("Hello world " + i).getBytes(StandardCharsets.UTF_8)));
        }

        //split the large batch into small ones:
        ListSplitter splitter = new ListSplitter(messages);
        while (splitter.hasNext()) {
            List<Message> listItem = splitter.next();
            SendResult sendResult = producer.send(listItem);
            System.out.printf("%s", sendResult);
        }
    }

}

class ListSplitter implements Iterator<List<Message>> {
    private static final int SIZE_LIMIT = 1000 * 1000;
    private final List<Message> messages;
    private int currIndex;

    public ListSplitter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int nextIndex = currIndex;
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++) {
            Message message = messages.get(nextIndex);
            int tmpSize = message.getTopic().length() + message.getBody().length;
            Map<String, String> properties = message.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                tmpSize += entry.getKey().length() + entry.getValue().length();
            }
            //for log overhead
            tmpSize = tmpSize + 20;
            if (tmpSize > SIZE_LIMIT) {
                //it is unexpected that single message exceeds the sizeLimit
                //here just let it go, otherwise it will block the splitting process
                if (nextIndex - currIndex == 0) {
                    //if the next sublist has no element, add this one and then break, otherwise just break
                    nextIndex++;
                }
                break;
            }
            if (tmpSize + totalSize > SIZE_LIMIT) {
                break;
            } else {
                totalSize += tmpSize;
            }

        }
        List<Message> subList = messages.subList(currIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not allowed to remove");
    }
}
