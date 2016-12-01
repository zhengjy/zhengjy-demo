package com.zhengjy.test.design.demo.状态模式.t1;


import com.zhengjy.test.design.demo.状态模式.State;

/**
 * Created by Administrator on 2016/11/19.
 */
public abstract class StateWrapper  implements State {
    /**
     *投入25分钱
     */
    public void insertQuarter(){}

    /**
     * 没有25分钱状态
     */
    public void ejectQuarter(){}

    /**
     * 转动曲柄
     */
    public void turnCrank(){}

    /**
     * 发放糖果
     */
    public void dispense(){}
}
