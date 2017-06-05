package com.zhengjy.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by zhengjy on 2017/1/9.
 */
public class SpringTest2 {
    public static void main(String[] args) {
        ApplicationContext a = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        a.getBean("fooBar");

            BeanFactory bf = new XmlBeanFactory(new ClassPathResource("spring/spring-config.xml"));
        System.out.println();
    }
}
