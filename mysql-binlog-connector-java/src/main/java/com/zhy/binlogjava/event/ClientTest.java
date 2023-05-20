package com.zhy.binlogjava.event;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.BinaryLogFileReader;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2023/5/12 22:29
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {
        BinaryLogClient client = new BinaryLogClient("zhouhongyin.top", 3308, "root", "1234567788");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(event -> {

            Event event1 = event;


            EventData data = event.getData();
            System.out.println(data);
        });
        client.connect();

    }


}
