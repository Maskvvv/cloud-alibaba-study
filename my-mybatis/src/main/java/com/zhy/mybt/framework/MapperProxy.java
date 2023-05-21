package com.zhy.mybt.framework;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.zhy.mybt.framework.annotation.Query;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/5/20 23:05
 */
public class MapperProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Query query = method.getAnnotation(Query.class);
        if (query == null) {
            return null;
        }
        Class<?> returnType = method.getReturnType();

        String sql = query.value();

        List<?> query1 = Db.use().query(sql, returnType, args);

        return query1;
    }
}
