package com.example.easylocktest.service;

import com.easy.lock.annotation.EasyLock;
import com.example.easylocktest.domain.DemoBody;
import com.example.easylocktest.domain.DemoParam;
import com.example.easylocktest.lock.DemoConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p> DemoService </p>
 *
 * @author zhouhongyin
 * @since 2024/1/16 11:25
 */
@Service
public class DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);


    /**
     * common use for easy-lock
     */
    @EasyLock(keyConvert = DemoConvert.class, spEl = "{{#body.name}}-{{#body.age}}-{{#userid}}")
    public String addUser(DemoParam param, DemoBody body) {

        System.out.println("addUser do something.....");
        System.out.println("param: " + param);
        System.out.println("body: " + body);

        return "success";
    }


}
