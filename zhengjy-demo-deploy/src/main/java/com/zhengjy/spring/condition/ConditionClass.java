package com.zhengjy.spring.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhengjy on 2017/7/15.
 */
@Configuration
@ConditionalOnMyProperties(name = "message")
public  class ConditionClass {
    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }
}
class HelloWorld {
    public void print() {
        System.out.println("hello world");
    }
}
