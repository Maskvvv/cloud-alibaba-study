package com.zhy.mybt;

import com.zhy.mybt.framework.annotation.MyMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhouhongyin
 * @since 2023/5/20 20:56
 */
@MyMapperScan(basePackage = "com.zhy.mybt.mapper")
@SpringBootApplication
public class MyBatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }

}
