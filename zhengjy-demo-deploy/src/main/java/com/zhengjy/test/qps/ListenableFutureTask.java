package com.zhengjy.test.qps;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by Jiyang.Zheng on 2019/1/22 14:07.
 */
public class ListenableFutureTask {

    public static void main(String[] args) {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<Object> submit = service.submit(new Callable<Object>() {
            public Object call() throws InterruptedException {
                Thread.sleep(1000);
                throw new RuntimeException();
            }
        });
        Futures.addCallback(submit, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("result = " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("t = " + t);

            }
        });
        System.out.println();
        System.out.println();
    }
}
