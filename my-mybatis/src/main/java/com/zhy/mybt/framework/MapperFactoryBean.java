package com.zhy.mybt.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.reflect.Proxy;

/**
 * @author zhouhongyin
 * @since 2023/5/20 22:50
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class rawMapperClass;

    public MapperFactoryBean() {
    }

    public MapperFactoryBean(Class rawMapperClass) {
        this.rawMapperClass = rawMapperClass;
    }

    @Override
    public T getObject() throws Exception {
        Object o = Proxy.newProxyInstance(
                rawMapperClass.getClassLoader(),
                new Class[]{rawMapperClass},
                new MapperProxy());

        return (T) o;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
