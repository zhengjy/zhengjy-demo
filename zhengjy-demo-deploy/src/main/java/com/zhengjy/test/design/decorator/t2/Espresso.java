package com.zhengjy.test.design.decorator.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/25 21:20.
 */
public class Espresso extends Beverage {

    public Espresso(){
        description = "Espresso";
    }


    @Override
    public double cost() {
        return 1.99;
    }
}
