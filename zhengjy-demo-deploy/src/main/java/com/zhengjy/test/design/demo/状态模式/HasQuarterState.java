package com.zhengjy.test.design.demo.状态模式;

import java.util.Random;

/**
 * Created by Administrator on 2016/11/19.
 * 有25分钱状态
 */
public class HasQuarterState implements  State {
    Random random = new Random(System.currentTimeMillis());
    GumballMachine gumballMachine;
    public  HasQuarterState(GumballMachine gumballMachine){
        this.gumballMachine =gumballMachine;
    }


    @Override
    public void insertQuarter() {//投入25分钱
        System.out.println("HasQuarterState.insertQuarter 投入25分钱");
    }

    @Override
    public void ejectQuarter() {//拒绝25分钱
        System.out.println("HasQuarterState.ejectQuarter，拒绝25分钱 退出顾客的钱，并将状态转换未投入钱状态|||||||||||");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {//转动手柄
        System.out.println("HasQuarterState.turnCrank， 转动手柄....|||||||||||");
        int winner =random.nextInt(10);
        if((winner==0) && (gumballMachine.getCount()>1)){
            gumballMachine.setState(gumballMachine.getWinnerState());
        }else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {//发放糖果
        System.out.println("HasQuarterState.dispense发放糖果");
    }
}
