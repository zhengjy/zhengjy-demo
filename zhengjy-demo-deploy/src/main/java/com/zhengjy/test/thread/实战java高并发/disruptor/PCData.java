package com.zhengjy.test.thread.实战java高并发.disruptor;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class PCData {
    private long value;
    public void  set(long value){
        this.value = value;
    }
    public long get(){
        return value;
    }
}
