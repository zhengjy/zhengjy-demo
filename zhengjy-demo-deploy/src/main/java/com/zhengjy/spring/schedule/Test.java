package com.zhengjy.spring.schedule;

import com.zhengjy.spring.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zhengjy on 2017/4/24.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
