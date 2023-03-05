package com.zhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SpringBootFlinkCdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFlinkCdcApplication.class, args);
    }

}
