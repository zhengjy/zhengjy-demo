package com.zhengjy.test.design.demo.状态模式;

/**
 * Created by Administrator on 2016/11/19.
 * 没有20分钱状态
 */
public class NoQuarterState implements State {
    GumballMachine gumballMachine;
    public NoQuarterState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {//投入25分钱
        System.out.println("NoQuarterState.insertQuarter() 投入25分钱.....执行转动|||||||||||");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {//拒绝25分钱
        System.out.println("NoQuarterState.ejectQuarter() 拒绝25分钱");
    }

    @Override
    public void turnCrank() {//转动曲柄
        System.out.println("NoQuarterState.turnCrank() 转动曲柄");
    }

    @Override
    public void dispense() {//发放糖果
        System.out.println("NoQuarterState.dispense() 发放糖果");
    }
}
