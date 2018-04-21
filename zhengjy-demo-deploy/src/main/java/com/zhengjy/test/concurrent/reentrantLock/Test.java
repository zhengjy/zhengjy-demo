package com.zhengjy.test.concurrent.reentrantLock;

/**
 * Created by zhengjy on 2018/3/24.
 */
public class Test {

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.interrupted());
    }
}
