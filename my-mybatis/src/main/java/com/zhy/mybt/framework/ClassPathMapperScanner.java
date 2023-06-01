package com.zhy.mybt.framework;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @author zhouhongyin
 * @since 2023/5/20 21:51
 */
//@Component
public class ClassPathMapperScanner extends ClassPathBeanDefinitionScanner {

    public ClassPathMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    //public void registerFilters() {
    //    // default include filter that accepts all classes
    //    addIncludeFilter((metadataReader, metadataReaderFactory) -> {
    //        // 跳过非ee的mapper,比如瞎几把写的接口,没有继承BaseEsMapper
    //        String className = metadataReader.getClassMetadata().get();
    //        try {
    //            Class<?> clazz = Class.forName(className);
    //            return BaseEsMapper.class.isAssignableFrom(clazz);
    //        } catch (ClassNotFoundException e) {
    //            logger.debug("mapper not found" + e);
    //        }
    //        return true;
    //    });
    //    addIncludeFilter(new AnnotationTypeFilter(Repository.class));
    //}


    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (CollectionUtils.isEmpty(beanDefinitionHolders)) return beanDefinitionHolders;

        reProcessBeanDefinition(beanDefinitionHolders);

        return beanDefinitionHolders;
    }

    private void reProcessBeanDefinition(Set<BeanDefinitionHolder> beanDefinitionHolders) {

        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            beanDefinition.setBeanClass(MapperFactoryBean.class);
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        }
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return true;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isInterface();
    }
}
