package com.qhbd.datasource.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:   主库注解
 * @Author:         qinwei
 * @CreateDate:     2018/9/3 0003 下午 4:43
 * @Version:        1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Master {
}
