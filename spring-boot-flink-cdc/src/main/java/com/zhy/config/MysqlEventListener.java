package com.zhy.config;

import com.ververica.cdc.connectors.mysql.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * mysql变更监听
 *
 * @author zhouhongyin
 * @since 2023/3/5 22:22
 */
@Component
public class MysqlEventListener implements ApplicationRunner {

    private final List<IDataChangeSink>  dataChangeSinks;

    @Resource
    private FlinkProperties flinkProperties;

    @Resource
    private ThreadPoolTaskExecutor flinkTaskThreadPool;

    @Autowired
    public MysqlEventListener(List<IDataChangeSink> dataChangeSinks) {
        this.dataChangeSinks = dataChangeSinks;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);

        DebeziumSourceFunction<DataChangeInfo> dataChangeInfoMySqlSource = buildDataChangeSource();
        DataStream<DataChangeInfo> streamSource = env
                .addSource(dataChangeInfoMySqlSource, "mysql-source")
                .setParallelism(2);

        for (IDataChangeSink dataChangeSink : dataChangeSinks) {
            streamSource.addSink(dataChangeSink);
        }

        env.execute("mysql-stream-cdc");

    }

    /**
     * 构造变更数据源
     *
     * @param
     * @return DebeziumSourceFunction<DataChangeInfo>
     * @author lei
     * @date 2022-08-25 15:29:38
     */
    private DebeziumSourceFunction<DataChangeInfo> buildDataChangeSource() {
        return MySqlSource.<DataChangeInfo>builder()
                .hostname(flinkProperties.getHostname())
                .port(flinkProperties.getPort())
                .databaseList(flinkProperties.getDatabaseList())
                .tableList(flinkProperties.getTableList())
                .username(flinkProperties.getUsername())
                .password(flinkProperties.getPassword())

                /**initial初始化快照,即全量导入后增量导入(检测更新数据写入)
                 * latest:只进行增量导入(不读取历史变化)
                 * timestamp:指定时间戳进行数据导入(大于等于指定时间错读取数据)
                 * 1956982
                 * 1957688
                 * 1957205
                 *
                 * 1958017
                 */
                //.startupOptions(StartupOptions.specificOffset("binlog.000005", 1957205))
                //.startupOptions(StartupOptions.timestamp(1684762706000L))
                .startupOptions(StartupOptions.latest())
                .deserializer(new MysqlDeserialization())
                .serverTimeZone("GMT+8")
                .build();
    }
}
