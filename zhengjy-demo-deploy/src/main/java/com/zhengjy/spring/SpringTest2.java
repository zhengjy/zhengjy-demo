package com.zhengjy.spring;

import com.zhengjy.spring.event.DemoPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengjy on 2017/1/9.
 */
public class SpringTest2 {
    public static void main(String[] args) {
        ApplicationContext a = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        DemoPublisher publisher = a.getBean(DemoPublisher.class);
        publisher.publish("hello appliction event");
    }
}
