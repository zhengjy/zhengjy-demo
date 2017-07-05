package com.zhengjy.test.thread.实战java高并发.producer_consumer;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class PCData {
    private final int intData;
    public PCData(int d){
        this.intData = d;
    }
    public PCData(String d){
        intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
