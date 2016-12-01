package com.zhengjy.test.design.demo.状态模式;

/**
 * Created by Administrator on 2016/11/19.
 * 售馨状态
 */
public class SoldOutState implements State {
    GumballMachine gumballMachine ;
    public SoldOutState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {//投入25分钱
        System.out.println("SoldOutState.投入25分钱");
    }

    @Override
    public void ejectQuarter() {//拒绝25分钱
        System.out.println("SoldOutState.拒绝25分钱");
    }

    @Override
    public void turnCrank() {//转动曲柄
        System.out.println("转动曲柄---哇哦。。。糖果售馨了，吃货。|||||||||||");
        gumballMachine.setSoldOutState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void dispense() {//发放糖果
        System.out.println("SoldOutState.发放糖果");
    }
}
