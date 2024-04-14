package com.zhy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.mapper.UserMapper;
import com.zhy.module.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
