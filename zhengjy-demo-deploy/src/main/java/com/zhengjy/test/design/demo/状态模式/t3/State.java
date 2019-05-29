package com.zhengjy.test.design.demo.状态模式.t3;

/**
 * 操作动作
 * Created by zhengjy on 2019/5/29.
 */
public interface State {
    /**
     * 投入硬币动作
     */
    void insertQuarter();

    /**
     * 退回硬币动作
     */
    void ejectQuarter();

    /**
     * 转动曲柄
     */
    void turnCrank();

    /**
     * 发放糖果动作
     */
    void dispense();
}
