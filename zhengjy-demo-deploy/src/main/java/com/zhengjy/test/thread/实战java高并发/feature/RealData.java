package com.zhengjy.test.thread.实战java高并发.feature;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class RealData implements Data {
    protected  final String result;
    public RealData(String para) throws InterruptedException {
        StringBuffer sb = new StringBuffer();
        //RealData的构造可能很慢，需要用户等待很久，这里是有sleep模拟
        for(int i=0;i<10;i++){
            sb.append(para);
            Thread.sleep(100);
        }
        result =sb.toString();
    }
    @Override
    public String getResult() {
        return result;
    }
}
