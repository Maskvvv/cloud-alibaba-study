package com.zhy.mybt.mapper;

import com.zhy.mybt.framework.MapperRegister;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

/**
 * @author zhouhongyin
 * @since 2023/5/20 20:59
 */
@Repository
public interface UserMapper {

    String getUserInfo();

}
