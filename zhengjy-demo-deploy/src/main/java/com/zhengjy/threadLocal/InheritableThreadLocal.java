package com.zhengjy.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengjy on 2016/12/1.
 */
public class InheritableThreadLocal {
    public static void main(String[] args) throws InterruptedException {
        final java.lang.InheritableThreadLocal<Span> inheritable = new java.lang.InheritableThreadLocal<Span>();
        inheritable.set(new Span("zhengjy---1"));
        System.out.println(inheritable.get().name);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("=========");
                System.out.println(inheritable.get().name);
                inheritable.set(new Span("zhengjy--2"));
                System.out.println(inheritable.get().name);

            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(runnable);
        Thread.currentThread().sleep(3000);
        executor.submit(runnable);
        Thread.currentThread().sleep(3000);
        System.out.println("=========");
        System.out.println(inheritable.get().name);
    }


}


class  Span{
    public String name;
    public int age;
    public Span(String name) {
        this.name = name;
    }
}