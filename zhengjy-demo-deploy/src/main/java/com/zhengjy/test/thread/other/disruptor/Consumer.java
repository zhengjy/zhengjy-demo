package com.zhengjy.test.thread.other.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData event) throws Exception {
        System.out.println("Thread ID="+Thread.currentThread().getId() +",:Event:--"+event.get() * event.get()+ "--");
    }
}
