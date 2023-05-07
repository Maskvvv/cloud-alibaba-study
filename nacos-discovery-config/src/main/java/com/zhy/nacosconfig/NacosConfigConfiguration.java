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
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.util.concurrent.Executor;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@Configuration
public class NacosConfigConfiguration {

    private final NacosConfigManager nacosConfigManager;

    @Value("${spring.cloud.nacos.config.extension-configs[0].group}")
    private String group;

    @Value("${spring.cloud.nacos.config.extension-configs[0].data-id}")
    private String dataId;

    public NacosConfigConfiguration(NacosConfigManager nacosConfigManager) {
        this.nacosConfigManager = nacosConfigManager;
    }

    @PostConstruct
    public void getConfig() throws NacosException {
        //NacosConfigManager nacosConfigManager = this.nacosConfigManager;
        //ConfigService configService = nacosConfigManager.getConfigService();
        //
        //String config = configService.getConfig(dataId, group, 2000);
        //
        //System.out.println(config);
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

                CharArrayReader charArrayReader = new CharArrayReader(configInfo.toCharArray());

                BufferedReader bufferedReader = new BufferedReader(charArrayReader);
                bufferedReader.readLine();

                System.out.println("--------------------------------------");

                String x = bufferedReader.readLine();
                while (x != null) {

                    System.out.println(x);
                    x = bufferedReader.readLine();
                }

            }
        });
    }


}
