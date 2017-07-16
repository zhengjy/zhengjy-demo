package com.zhengjy.spring.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * Created by zhengjy on 2017/7/15.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnMyPropertiesCondition.class)
public @interface ConditionalOnMyProperties {
    String name();
}
