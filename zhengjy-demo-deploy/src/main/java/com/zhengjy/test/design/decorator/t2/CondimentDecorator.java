package com.zhengjy.test.design.decorator.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/25 21:15.
 * 装饰线Condiment(调料) 抽象类，装饰类
 *
 * 首先让CondimentDecorator 能够去掉Beverage，所以将CondimentDecorator扩展自Beverage类
 */
public abstract class CondimentDecorator extends Beverage{
    /**
     * 所有的调料装饰者必须首先getDecription方法。
     * @return
     */
    public abstract String getDescription();
}
