package com.zhengjy.spring;

import com.zhengjy.spring.user.UserService;
import com.zhengjy.spring.user.UserServiceImpl1;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by zhengjy on 2016/12/19.
 */
public class SpringTest {
    /*
    BeanFactory 只是一个接口，我们最终需要一个该接口的实现来进行实际的Bean的管理， Default-
        ListableBeanFactory 就是这么一个比较通用的 BeanFactory 实现类。 DefaultListableBean-
        Factory 除了间接地实现了 BeanFactory 接口，还实现了 BeanDefinitionRegistry 接口，该接口才
        是在 BeanFactory 的实现中担当Bean注册管理的角色。基本上， BeanFactory 接口只定义如何访问容
        器内管理的Bean的方法，各个 BeanFactory 的具体实现类负责具体Bean的注册以及管理工作。
        BeanDefinitionRegistry 接口定义抽象了Bean的注册逻辑。通常情况下，具体的 BeanFactory 实现
        类会实现这个接口来管理Bean的注册
     */
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultFactory = new DefaultListableBeanFactory();
        BeanFactory bf =bindViaCode(defaultFactory);
        UserService us = (UserService) bf.getBean("user1");
        System.out.println(us.getName());
    }

    @Test
    public void  test2(){
       /* ResourceLoader resourceLoader = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        // ResourceLoader resourceLoader = new FileSystemXmlApplicationContext("
        Resource fileResource = resourceLoader.getResource("D:/spring21site/README");
        assertTrue(fileResource instanceof ClassPathResource);
        assertFalse(fileResource.exists());
        Resource urlResource2 = resourceLoader.getResource("http://www.spring21.cn");
        assertTrue(urlResource2 instanceof UrlResource);*/
    }
    private   static BeanFactory bindViaCode(BeanDefinitionRegistry registry){
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("classpath:../news-config.xml");


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


    @Test
    private  void  testSpring(){
        ApplicationContext a = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        a.getBean("");
    }
}
