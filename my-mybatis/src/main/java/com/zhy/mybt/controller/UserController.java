package com.zhy.mybt.controller;

import com.zhy.mybt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2023/5/20 21:03
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    public String getUserInfo() {

        return userMapper.getUserInfo();
    }

}
