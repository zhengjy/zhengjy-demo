package com.zhengjy.test.design.demo.状态模式;

/**
 * Created by Administrator on 2016/11/19.
 * 售出糖果
 */
public class SoldState implements State {
    GumballMachine gumballMachine;
    public SoldState(GumballMachine gumballMachine){
        this.gumballMachine =gumballMachine;
    }


    @Override
    public void insertQuarter() {//投入25分钱
        System.out.println("SoldState.投入25分钱");
    }

    @Override
    public void ejectQuarter() {//拒绝25分钱
        System.out.println("SoldState.拒绝25分钱");
    }

    @Override
    public void turnCrank() {//转动曲柄
        System.out.println("SoldState.转动曲柄");
    }

    @Override
    public void dispense() {//发放糖果
        System.out.println("SoldState.发放糖果|||||||||||");
        gumballMachine.releaseBall();//顾客已经付钱，需要机器方法糖果
        if (gumballMachine.getCount()>0){
            //糖果售馨
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }else {
            //发放糖果
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}
