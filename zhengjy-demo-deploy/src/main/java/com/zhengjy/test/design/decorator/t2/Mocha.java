package com.zhengjy.test.design.decorator.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/25 21:24.
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Mocha";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
