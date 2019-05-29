package com.zhengjy.test.design.demo.状态模式.t3;

/**
 * Created by zhengjy on 2019/5/29.
 */
public class WinnerState implements State {
    private GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    @Override
    public void insertQuarter() {
        System.out.println("WinnerState发放糖果状态->不可投入硬币");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("WinnerState发放糖果状态-> 不可以退出钱");
    }

    @Override
    public void turnCrank() {
        System.out.println("WinnerState发放糖果状态-> 不可以转动曲柄");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0){
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }else {
            gumballMachine.releaseBall();
            //如果还有第二颗的话
            if (gumballMachine.getCount() > 0){
                //发放完糖果，将状态设置未投放硬币
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            }else {
                System.out.println("糖果售空了！！！");
                //将糖果转到售空状态
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }

    }
}
