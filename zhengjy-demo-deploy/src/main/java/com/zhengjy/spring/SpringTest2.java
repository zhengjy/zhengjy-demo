package com.zhengjy.spring;

import com.zhengjy.spring.event.DemoPublisher;
import com.zhengjy.spring.factory_bean.Car;
import com.zhengjy.spring.factory_bean.CarFactoryBean;
import com.zhengjy.spring.循环依赖.Boss;
import com.zhengjy.spring.循环依赖.Office;
import com.zhengjy.tag.User;
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
       /* User u = (User) bf.getBean("user");
        User u2 = (User) bf.getBean("user");
        Car car = (Car) bf.getBean("car");
        CarFactoryBean cf = (CarFactoryBean) bf.getBean("&car");*/
        Boss boss = (Boss) bf.getBean("boss");
        Boss boss2 = (Boss) bf.getBean("boss");
        Office office = (Office) bf.getBean("office");
        /*DemoPublisher publisher = bf.getBean(DemoPublisher.class);
        publisher.publish("hello appliction event");*/
    }
}
