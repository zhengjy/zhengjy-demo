package com.zhengjy.spring.spring_mvc_return_val;

/**
 * Created by zhengjy on 2017/11/30 21:28
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonAnnotation {
    String value() default "";
}
