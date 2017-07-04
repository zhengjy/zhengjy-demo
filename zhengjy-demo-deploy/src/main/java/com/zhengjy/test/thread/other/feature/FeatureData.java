package com.zhengjy.test.thread.other.feature;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class FeatureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();//RealData已经被注入，通知getResult()
    }
    @Override
    public synchronized String getResult() throws InterruptedException {
        //会等待RealData构造完成
        while (!isReady){
            //一直等待，直到RealData被注入
            wait();
        }
        return realData.result;
    }

}
