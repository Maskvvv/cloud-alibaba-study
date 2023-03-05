package com.zhy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2023/3/5 22:28
 */
@Data
@Component
@ConfigurationProperties("flink")
public class FlinkProperties {
    private String hostname;

    private Integer port;

    private String databaseList;

    private String tableList;

    private String username;

    private String password;



}
