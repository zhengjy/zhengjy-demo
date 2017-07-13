package com.zhengjy.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

/**
 * Created by zhengjy on 2017/7/13.
 */
public class SpringApplicationRunListenerTest implements SpringApplicationRunListener {
    private SpringApplication application;

    /**
     * 必须有这个构造函数，否则spring无法初始化该类
     * @param application
     * @param args
     */
    public SpringApplicationRunListenerTest(SpringApplication application, String[] args) {
        this.application = application;
    }
    //刚执行run方法时
    @Override
    public void started() {
        System.out.println("---1---");
    }
    //环境建立好时候
    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
        System.out.println("---2---");
    }
    //上下文建立好的时候
    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("---3---");
    }
    //上下文载入配置时候
    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("---4---");
    }
    //上下文刷新完成后，run方法执行完之前
    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {
        System.out.println("---5---");

        Class clazz = null;
        Field field = null;
        try {
            clazz = Class.forName("java.lang.ApplicationShutdownHooks");
            field = clazz.getDeclaredField("hooks");
            field.setAccessible(true);
            IdentityHashMap<Thread, Thread> hooks = (IdentityHashMap<Thread, Thread>) field.get(null);
            System.out.println(hooks);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
