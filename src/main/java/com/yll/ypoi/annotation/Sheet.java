package com.yll.ypoi.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Sheet
 * @description: sheet名注解
 * @author: akira
 * @date: Created in 2018/11/22 22:50
 * @modify by: akira
 * @version: V1.0
 */
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Sheet {

    String name();

}
