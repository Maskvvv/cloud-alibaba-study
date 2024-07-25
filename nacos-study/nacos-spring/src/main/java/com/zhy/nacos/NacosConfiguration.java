package com.zhy.nacos;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
//-Dnacos.server-addr=server-addr -Dnacos.namespace=dev
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "39.106.49.241:8848", namespace = "dev"))
@NacosPropertySource(dataId = "dev.properties", autoRefreshed = true)
public class NacosConfiguration {

}
