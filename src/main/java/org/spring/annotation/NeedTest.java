package org.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //声明注解的保留期限
@Target(ElementType.METHOD) //声明可以使用该注解的目标类型
public @interface NeedTest {
    /**
     * 声明注解成员
     *  成员以无入参、无抛出异常的方式声明
     *  成员类型受限的（包括原始类型及其封装类）
     *
     */
    boolean value() default true;
}
