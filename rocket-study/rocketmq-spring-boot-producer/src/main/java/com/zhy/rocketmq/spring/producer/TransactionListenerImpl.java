package com.zhy.rocketmq.spring.producer;

import com.google.common.base.Utf8;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.StringMessageConverter;

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String destination = ((String) arg);

        org.apache.rocketmq.common.message.Message message = RocketMQUtil.convertToRocketMessage(new StringMessageConverter(), "utf-8", destination, msg);

        String tags = message.getTags();
        if (StringUtils.contains(tags, "TagA")) {
            return RocketMQLocalTransactionState.COMMIT;
        } else if (StringUtils.contains(tags, "TagB")) {
            return RocketMQLocalTransactionState.ROLLBACK;
        } else if (StringUtils.contains(tags, "TagC")) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {

        return null;
    }
}
