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
public class DataChangeTestSink implements IDataChangeSink {

    @Override
    public void invoke(DataChangeInfo value, Context context) throws Exception {
        log.info(value.getFileName());
        log.info(value.getFilePos());
        log.info(value.getChangeTime());
        log.info(value.getAfterData());
    }
}
