package com.zhy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.module.User;

import java.io.Serializable;

/**
 * @author zhouhongyin
 * @since 2024/4/14 11:04
 */
public interface UserMapper extends BaseMapper<User> {

    User selectById11(Integer id);
}
