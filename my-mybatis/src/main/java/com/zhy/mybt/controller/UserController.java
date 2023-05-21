package com.zhy.mybt.controller;

import com.zhy.mybt.mapper.UserMapper;
import com.zhy.mybt.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/5/20 21:03
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserInfo> getUserInfo() {

        return userMapper.getUserInfo();
    }

}
