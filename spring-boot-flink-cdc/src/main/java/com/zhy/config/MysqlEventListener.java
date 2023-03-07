package com.zhy.config;

import com.ververica.cdc.connectors.mysql.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * mysql变更监听
 *
 * @author zhouhongyin
 * @since 2023/3/5 22:22
 */
@Component
public class MysqlEventListener implements ApplicationRunner {

    private final DataChangeSink dataChangeSink;

    @Resource
    private FlinkProperties flinkProperties;

    @Autowired
    public MysqlEventListener(DataChangeSink dataChangeSink) {
        this.dataChangeSink = dataChangeSink;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DebeziumSourceFunction<DataChangeInfo> dataChangeInfoMySqlSource = buildDataChangeSource();
        DataStream<DataChangeInfo> streamSource = env
                .addSource(dataChangeInfoMySqlSource, "mysql-source")
                .setParallelism(1);

        streamSource.addSink(dataChangeSink);

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
                 */
                .startupOptions(StartupOptions.initial())
                .deserializer(new MysqlDeserialization())
                .serverTimeZone("GMT+8")
                .build();
    }
}
