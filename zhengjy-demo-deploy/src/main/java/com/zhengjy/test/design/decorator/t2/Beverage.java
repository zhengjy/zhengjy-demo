package com.zhengjy.test.design.decorator.t2;

/**
 *饮料抽象类
 * Created by Jiyang.Zheng on 2019/5/25 21:09
    Beverage是一个抽象类，有两个方法，setDesciption
 */
public abstract class Beverage {

     protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
