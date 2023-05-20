package com.zhy.mybt.framework.annotation;

import com.zhy.mybt.framework.MapperRegister;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouhongyin
 * @since 2023/5/20 21:39
 */
@Import(MapperRegister.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyMapperScan {

    @AliasFor("basePackage")
    String[] value() default "";

    @AliasFor("value")
    String[] basePackage() default "";
}
