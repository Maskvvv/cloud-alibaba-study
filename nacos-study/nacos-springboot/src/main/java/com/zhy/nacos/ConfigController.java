package com.zhy.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class ConfigController {

    @NacosValue(value = "${age:111}", autoRefreshed = true)
    private String age;

    @GetMapping(value = "/get")
    public String get() {
        return age;
    }

    @Autowired
    private TestNacosConfiguration testNacosConfiguration;

    @GetMapping(value = "/conf")
    public String getConf() {
        return testNacosConfiguration.toString();
    }


}
