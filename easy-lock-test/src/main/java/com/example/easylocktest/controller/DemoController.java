package com.example.easylocktest.controller;


import com.example.easylocktest.domain.DemoBody;
import com.example.easylocktest.domain.DemoParam;
import com.example.easylocktest.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/16 11:25
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;


    @PostMapping("add")
    private String addUser(DemoParam param, @RequestBody DemoBody body) {
        String result = demoService.addUser(param, body);
        return result;
    }


}
