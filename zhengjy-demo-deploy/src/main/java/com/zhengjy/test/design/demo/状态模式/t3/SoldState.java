package com.zhengjy.test.design.demo.状态模式.t3;

/**
 * Created by zhengjy on 2019/5/29.
 * 发放糖果状态
 */
public class SoldState implements State {
    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("发放糖果状态->不可投入硬币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("发放糖果状态-> 不可以退出钱");

    }

    @Override
    public void turnCrank() {
        System.out.println("发放糖果状态-> 不可以转动曲柄");

    }

    @Override
    public void dispense() {
        //顾客已经付钱，机器发放糖果,count -1
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0){
            //发放完糖果，将状态设置未投放硬币
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }else{
            System.out.println("糖果售空了！！！");
            //将糖果转到售空状态
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }

    }
}
