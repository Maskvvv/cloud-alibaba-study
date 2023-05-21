package com.zhy.mybt.mapper;

import com.zhy.mybt.framework.MapperRegister;
import com.zhy.mybt.framework.annotation.Query;
import com.zhy.mybt.model.UserInfo;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/5/20 20:59
 */
@Repository
public interface UserMapper {

    @Query("select * from ourea.sort")
    List<UserInfo> getUserInfo();

}
