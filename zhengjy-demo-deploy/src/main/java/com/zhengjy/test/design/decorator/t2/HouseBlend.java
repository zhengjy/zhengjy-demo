package com.zhengjy.test.design.decorator.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/25 21:22.
 */
public class HouseBlend extends Beverage {
    public HouseBlend(){
        description = "House Blend Coffee";
    }
    @Override
    public double cost() {
        return .89;
    }
}
