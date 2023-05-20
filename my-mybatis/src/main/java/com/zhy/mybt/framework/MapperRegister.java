package com.zhy.mybt.framework;

import com.zhy.mybt.framework.annotation.MyMapperScan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zhouhongyin
 * @since 2023/5/20 21:46
 */

public class MapperRegister implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MyMapperScan.class.getName()));
        String[] values = annotationAttributes.getStringArray("value");


        ClassPathMapperScanner classPathMapperScanner = new ClassPathMapperScanner(registry);

        classPathMapperScanner.doScan(values);
    }
}
