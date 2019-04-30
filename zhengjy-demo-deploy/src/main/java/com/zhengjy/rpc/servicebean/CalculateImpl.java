package com.zhengjy.rpc.servicebean;


/**
 * Created by Jiyang.Zheng on 2019/1/11 16:36.
 */
public class CalculateImpl implements Calculate {
    //两数相加
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
