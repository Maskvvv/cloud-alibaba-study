package com.zhy;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
@EsMapperScan("com.zhy.es.mapper")
public class SpringBootFlinkCdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFlinkCdcApplication.class, args);
    }

}
