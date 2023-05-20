package com.zhy.mybt.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhouhongyin
 * @since 2023/5/20 23:05
 */
public class MapperProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



        return "我是结果";
    }
}
