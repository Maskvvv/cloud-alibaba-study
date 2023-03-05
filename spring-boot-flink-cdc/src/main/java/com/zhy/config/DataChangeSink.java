package com.zhy.config;

import lombok.extern.log4j.Log4j2;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.stereotype.Component;


/**
 *
 * @author zhouhongyin
 * @since 2023/3/5 23:04
 */
@Component
@Log4j2
public class DataChangeSink implements SinkFunction<DataChangeInfo> {

    @Override
    public void invoke(DataChangeInfo value, Context context) {
        log.info("收到变更原始数据:{}", value);
        // todo 数据处理;因为此sink也是交由了spring管理，您想进行任何操作都非常简单
    }
}
