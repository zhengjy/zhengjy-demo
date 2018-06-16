package com.zhengjy.test.concurrent;

import com.zhengjy.test.design.master_worker.Master;

import java.util.concurrent.*;

/**
 * Created by Jiyang.Zheng on 2018/5/14 14:48.
 */
public class Test {
    static ExecutorService executor = Executors.newFixedThreadPool(5);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> str = executor.submit(() -> {
            Thread.sleep(6000);
           return "aaa";
        });
        String s =  str.get();

        System.out.println(s);
    }
}
