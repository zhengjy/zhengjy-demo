package com.zhengjy.test.thread.other.feature;

/**
 * Created by zhengjy on 2017/7/2.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        //这里会理解返回，因为得到是FeatureData而不是RealData
        Data data = client.request("name");
        System.out.println("请求完毕");

        //这里看看可以用一个sleep代替业务逻辑，
        Thread.sleep(2000);
        //使用真实的数据
        System.out.println("数据=" + data.getResult());
    }
}
