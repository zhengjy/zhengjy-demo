package com.zhengjy.spring.condition.t1;

/**
 * Created by zhengjy on 2017/7/15.
 */

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        CommandService cs = context.getBean(CommandService.class);
        System.out.println(cs.showListCommand());
        context.close();
    }
}
