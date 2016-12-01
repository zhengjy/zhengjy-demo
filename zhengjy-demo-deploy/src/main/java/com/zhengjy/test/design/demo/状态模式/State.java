package com.zhengjy.test.design.demo.状态模式;

/**
 * Created by Administrator on 2016/11/19.
 */
public interface State {
    /**
     *投入25分钱
     */
    void insertQuarter();

    /**
     * 没有25分钱状态
     */
    void ejectQuarter();

    /**
     * 转动曲柄
     */
    void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();
}
