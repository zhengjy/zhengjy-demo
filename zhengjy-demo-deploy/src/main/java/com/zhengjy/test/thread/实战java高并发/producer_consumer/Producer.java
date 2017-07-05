package com.zhengjy.test.thread.实战java高并发.producer_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    //内存缓冲区
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;
    //总数，原子操作
    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data = null;
        Random random = new Random();
        System.out.println("start producer id="+Thread.currentThread().getId());
        try {
            while (isRunning){
                Thread.sleep(random.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(data+" is put into queue");
                //提交数据到缓冲区
                if(!queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("failed to put data: "+ data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void  stop(){
        isRunning = false;
    }
}
