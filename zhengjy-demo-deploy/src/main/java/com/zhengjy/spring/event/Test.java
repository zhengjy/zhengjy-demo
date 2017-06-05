package com.zhengjy.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zhengjy on 2017/4/24.
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher dp = annotation.getBean(DemoPublisher.class);
        dp.publish("hello event !!!");

    }
}
