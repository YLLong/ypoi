package com.yll.ypoi.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Excel
 * @description: 自定义注解
 * @author: yys1778
 * @date: Created in 2018/11/14 16:16
 * @modify by: yys1778
 * @version: V1.0
 */
@Component
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

    String name();

}
