package com.zhy.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.zhy.es.document.MemberProfile;
import com.zhy.es.mapper.MemberProfileMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.annotation.Resource;


/**
 *
 * @author zhouhongyin
 * @since 2023/3/5 23:04
 */
public interface IDataChangeSink extends SinkFunction<DataChangeInfo> {

}
