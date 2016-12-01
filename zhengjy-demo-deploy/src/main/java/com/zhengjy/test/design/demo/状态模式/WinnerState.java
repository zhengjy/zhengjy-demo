package com.zhengjy.test.design.demo.状态模式;

/**
 * Created by Administrator on 2016/11/19.
 */
public class WinnerState implements State{
    GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    // 投入25分钱
    @Override
    public void insertQuarter() {
        // TODO Auto-generated method stub
        System.out.println("Please wait, we're already giving you a gumball");
    }
    // 拒绝25分钱
    @Override
    public void ejectQuarter() {
        // TODO Auto-generated method stub
        System.out.println("Sorry,you have already turn crank");
    }
    // 转动曲柄
    @Override
    public void turnCrank() {
        // TODO Auto-generated method stub
        System.out.println("trun twice ,doesn't give you anthor gamball!");
    }
    // 发放糖果
    @Override
    public void dispense() {
        // TODO Auto-generated method stub
        System.out.println("You're a Winner! You get two gumballs for your quarter");
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState());
        } else {
            gumballMachine.releaseBall();
            if(gumballMachine.getCount()>0){
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("Opps,out of gamball!");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }
}
