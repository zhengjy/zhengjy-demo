package com.zhengjy.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengjy on 2017/1/9.
 */
public class SpringTest2 {
    public static void main(String[] args) {
        ApplicationContext a = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        a.getBean("");
    }
}
