package com.zhengjy.test.thread.other.feature.t2;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class FutureMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> future = new FutureTask<String>(new RealData("xxx"));
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.submit(future);
        System.out.println("请求完毕");
        Thread.sleep(2000);
        System.out.println("数据=" + future.get());
        System.out.println("--end");
    }
}
