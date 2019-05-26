package com.zhengjy.test.design.decorator.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/25 21:26.
 */
public class Main {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() +"$"+beverage.cost());

        Beverage beverage1 = new HouseBlend();
        beverage1 =  new Mocha(beverage1);
        System.out.println(beverage1.getDescription() +"$"+beverage1.cost());
    }
}