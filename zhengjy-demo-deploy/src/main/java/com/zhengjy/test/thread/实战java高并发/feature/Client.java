package com.zhengjy.test.thread.实战java高并发.feature;


/**
 * Created by zhengjy on 2017/7/2.
 */
public class Client {
    public Data request(final String queryStr){
        final FeatureData future = new FeatureData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData  realData = null;
                try {
                    //RealData构造很慢，所以在单独的线程进行
                    realData = new RealData(queryStr);
                    future.setRealData(realData);
                } catch (InterruptedException e) {
                }
            }
        });
        //Feature会立即被返回
        return future;
    }
}
