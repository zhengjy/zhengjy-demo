package com.zhengjy.test.design.demo.状态模式.t3;

/**
 * Created by zhengjy on 2019/5/29.
 */
public class SoldOutState implements State{
    private GumballMachine gumballMachine;
    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("糖果售空了-》无需投放硬币");

    }

    @Override
    public void ejectQuarter() {
        //糖果售空，退出硬币
        gumballMachine.ejectQuarter();
        //转到已经未

    }

    @Override
    public void turnCrank() {
        System.out.println("糖果售空了-》无需转动曲柄");
    }

    @Override
    public void dispense() {
        System.out.println("糖果售空了-》无法发放");
    }
}
