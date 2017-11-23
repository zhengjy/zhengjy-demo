package com.zhengjy.test.netty_in_action.collback;

/**
 * Created by zhengjy on 2017/10/27 11:50
 */
public class Data {

    private int n;
    private int m;

    public Data(int n,int m){
        this.n = n;
        this.m = m;
    }

    @Override
    public String toString() {
        return "Data{" +
                "n=" + n +
                ", m=" + m +
                '}';
    }
}
