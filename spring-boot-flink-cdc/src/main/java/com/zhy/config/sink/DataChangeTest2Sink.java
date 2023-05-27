package com.zhy.config.sink;

import com.zhy.config.DataChangeInfo;
import com.zhy.config.IDataChangeSink;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2023/5/22 17:57
 */
@Log4j2
@Component
public class DataChangeTest2Sink implements IDataChangeSink {

    @Override
    public void invoke(DataChangeInfo value, Context context) throws Exception {
        log.info("DataChangeTest2Sink start----------");

        Thread.sleep(5000);
        log.info("DataChangeTest2Sink" + value.getAfterData());
    }
}
