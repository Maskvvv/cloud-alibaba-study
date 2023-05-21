package com.zhy.mybt.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.lang.reflect.Proxy;

/**
 * 在 Spring 中，FactoryBean 是一种特殊的 Bean，它的作用是用于创建其他的 Bean。它可以通过编码的方式，
 * 根据不同的条件创建不同的对象，也可以通过 XML 配置文件的方式，根据不同的配置创建不同的对象。
 *
 * FactoryBean 接口定义了三个方法：
 * - etObject()：返回由 FactoryBean 创建的 Bean 实例。
 * - getObjectType()：返回由 FactoryBean 创建的 Bean 的 Class。
 * - isSingleton()：返回由 FactoryBean 创建的 Bean 是否是单例的。
 *
 * 通过实现 FactoryBean 接口，可以让 Spring 容器在获取 Bean 时，返回由 FactoryBean 创建的 Bean 实例，
 * 而不是 FactoryBean 本身的实例。这样做的好处是可以灵活地根据条件创建不同的 Bean，同时也可以让 Spring 容器管理 Bean 的生命周期。
 *
 * @author zhouhongyin
 * @since 2023/5/20 22:50
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> rawMapperClass;

    public MapperFactoryBean() {
    }

    public MapperFactoryBean(Class<T> rawMapperClass) {
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
        return rawMapperClass;
    }
}
