package com.zhy.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("/get/{id}")
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "1"),
                    @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "60000"),
            },
            ignoreExceptions = {IllegalArgumentException.class},
            fallbackMethod = "errorCallBack"
    )   //模仿没有这个数据时，服务降级
    public Object get(@PathVariable("id") long id){
        if( id == 1){
            throw new RuntimeException("查无此产品");
        }
        return "success";
    }

    //指定一个降级的方法
    public Object errorCallBack(@PathVariable("id") long id){
        return id+"不存在,error";
    }
}