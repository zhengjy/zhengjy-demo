package com.zhengjy.spring;

import com.zhengjy.spring.user.UserService;
import com.zhengjy.spring.user.UserServiceImpl1;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Created by zhengjy on 2016/12/19.
 */
public class SpringTest {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultFactory = new DefaultListableBeanFactory();
        BeanFactory bf =bindViaCode(defaultFactory);
        UserService us = (UserService) bf.getBean("user1");
        System.out.println(us.getName());
    }

    private   static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
        AbstractBeanDefinition userInterface = new RootBeanDefinition(UserService.class);

        AbstractBeanDefinition user1 = new RootBeanDefinition(UserServiceImpl1.class);
        AbstractBeanDefinition user2= new RootBeanDefinition(UserServiceImpl1.class);

        // 将bean定义注册到容器中
        registry.registerBeanDefinition("userInterface", userInterface);
        registry.registerBeanDefinition("user1", user1);
        registry.registerBeanDefinition("user2", user2);

        //通过setter方法注入方式
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("user1",user1));
        propertyValues.addPropertyValue(new PropertyValue("user2",user2));

        userInterface.setPropertyValues(propertyValues);

        return (BeanFactory) registry;
    }
}
