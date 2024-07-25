package com.zhy.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouhongyin
 * @since 2024/7/25 22:32
 */
@Data
@Configuration
@NacosConfigurationProperties(prefix = "a", dataId = "a.properties",
        autoRefreshed = true, ignoreNestedProperties = true)
public class TestNacosConfiguration {

    private String name;
    private String age;
}
