package com.zhy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhouhongyin
 * @since 2023/3/7 15:27
 */
@Configuration
public class FlinkConfiguration {

    @Bean
    public ThreadPoolTaskExecutor flinkTaskThreadPool() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setMaxPoolSize(10);
        poolTaskExecutor.setMaxPoolSize(10);
        poolTaskExecutor.setQueueCapacity(100);
        poolTaskExecutor.setThreadNamePrefix("flink-task-");

        return poolTaskExecutor;
    }

}
