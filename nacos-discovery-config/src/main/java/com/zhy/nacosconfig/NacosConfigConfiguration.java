/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhy.nacosconfig;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@Configuration
public class NacosConfigConfiguration {

    private final NacosConfigManager nacosConfigManager;

    @Value("${spring.cloud.nacos.config.extension-configs[1].group}")
    private String group;

    @Value("${spring.cloud.nacos.config.extension-configs[1].data-id}")
    private String dataId;

    public NacosConfigConfiguration(NacosConfigManager nacosConfigManager) {
        this.nacosConfigManager = nacosConfigManager;
    }

    @PostConstruct
    public void getConfig() throws NacosException {
        NacosConfigManager nacosConfigManager = this.nacosConfigManager;
        ConfigService configService = nacosConfigManager.getConfigService();

        String config = configService.getConfig("easy-flink.conf", group, 2000);

        System.out.println(config);
    }

    @PostConstruct
    public void addListener() throws NacosException {

        NacosConfigManager nacosConfigManager = this.nacosConfigManager;
        ConfigService configService = nacosConfigManager.getConfigService();

        log.info("-------------------------addListener---------------------------");

        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @SneakyThrows
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("--------------------------------------");

                Config load = ConfigFactory.parseString(configInfo);

                //load.entrySet().forEach(entry -> {
                //    System.out.println(entry.getKey() + "\t" + entry.getValue());
                //});

                String mapping = load.getString("mapping");
                System.out.println(mapping);

                List<String> like = load.getStringList("like");
                for (String s : like) {
                    System.out.println(s);
                }


            }
        });
    }


}
