package com.zhengjy.spring.u2;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by zhengjy on 2017/1/9.
 */
public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext a = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        GetBeanTest gb = (GetBeanTest) a.getBean("getBeanTest");
        gb.showMe();
    }
}
