package com.zhengjy.test.thread.other.disruptor;

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
