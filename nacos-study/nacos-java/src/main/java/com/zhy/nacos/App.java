package com.zhy.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Hello world!
 */
public class App {

    private ConfigService configService;
    private String dataId = "dev.properties";
    private String group = "DEFAULT_GROUP";
    private String serverAddr;
    private String namespace = "dev";

    @Before
    public void init () throws NacosException {


        serverAddr = System.getenv().get("my_server_host") + ":8848";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", namespace);
        configService = NacosFactory.createConfigService(properties);
    }

    @Test
    public void getConfig() {
        try {
            String serverAddr = "39.106.49.241:8848";
            String dataId = "dev.properties";
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            properties.put("namespace", "dev");
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void listener() throws NacosException {


        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("recieve1:" + configInfo);
            }
            @Override
            public Executor getExecutor() {
                return null;
            }
        });

// 测试让主线程不退出，因为订阅配置是守护线程，主线程退出守护线程就会退出。 正式代码中无需下面代码
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
