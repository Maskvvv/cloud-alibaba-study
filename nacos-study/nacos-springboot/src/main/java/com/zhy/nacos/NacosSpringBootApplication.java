package com.zhy.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@NacosPropertySource(dataId = "dev.properties", autoRefreshed = true)
@SpringBootApplication
public class NacosSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosSpringBootApplication.class, args);
        System.out.println("Hello World!");
    }
}
