package com.zhengjy.test.thread.实战java高并发.producer_consumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id="+Thread.currentThread().getId());
        Random random = new Random();
        try{
            while (true){
                PCData data = queue.take();//提取任务
                if(null != data){
                    int re = data.getIntData() * data.getIntData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}",
                            data.getIntData(),data.getIntData(),re));
                    Thread.sleep(random.nextInt(SLEEPTIME));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}






