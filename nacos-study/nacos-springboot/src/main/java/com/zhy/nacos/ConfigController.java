package com.zhy.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
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

    @NacosValue(value = "${c:222}", autoRefreshed = true)
    private String c;

    @GetMapping(value = "/c")
    public String c() {
        return c;
    }


    @NacosValue(value = "${b:222}", autoRefreshed = true)
    private String b;

    @GetMapping(value = "/b")
    public String b() {
        return b;
    }


}
