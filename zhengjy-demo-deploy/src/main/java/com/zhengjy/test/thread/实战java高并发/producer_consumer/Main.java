package com.zhengjy.test.thread.实战java高并发.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //创建缓冲区
        BlockingQueue<PCData> queue = new LinkedBlockingDeque<>(10);
        //创建生产者
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Producer p3 = new Producer(queue);
        Producer p4 = new Producer(queue);
        //创建消费者
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        Consumer c4 = new Consumer(queue);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(p1);
        es.execute(p2);
        es.execute(p3);
        es.execute(p4);
        es.execute(c1);
        es.execute(c2);
        es.execute(c3);
        es.execute(c4);
        Thread.sleep(10000);
        p1.stop();
        p2.stop();
        p3.stop();
        p4.stop();
        Thread.sleep(3000);
        es.shutdown();



    }
}
