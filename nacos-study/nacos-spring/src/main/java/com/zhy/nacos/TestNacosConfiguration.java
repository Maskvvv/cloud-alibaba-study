package com.zhy.nacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@NacosConfigurationProperties(prefix = "a", dataId = "a.properties",
        autoRefreshed = true, ignoreNestedProperties = true)
public class TestNacosConfiguration {

    private String name;
    private String age;
}
