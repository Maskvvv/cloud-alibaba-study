package com.zhy;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EsMapperScan("com.zhy.es.mapper")
public class SpringBootEasyESApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEasyESApplication.class, args);
    }

}
