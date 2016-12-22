package com.zhengjy.spring.news;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Created by zhengjy on 2016/12/22.
 */
@Component
public class FooBar implements ApplicationContextAware {
    /**
     * 让一个类获取ApplicationContext
     */
    private ResourceLoader resourceLoader;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.resourceLoader = applicationContext;
    }

    public void foo(String location)
    {
        System.out.println(resourceLoader.getResource(location).getClass());
    }

    public static void main(String[] args) {
        FooBar f = new FooBar();
        f.foo("classpath:spring/spring-config.xml");
    }
}

