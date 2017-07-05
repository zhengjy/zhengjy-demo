package com.zhengjy.test.thread.实战java高并发.feature.t2;

import java.util.concurrent.Callable;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++){
            sb.append(para);
            Thread.sleep(1000);
        }
        return sb.toString();
    }
}
