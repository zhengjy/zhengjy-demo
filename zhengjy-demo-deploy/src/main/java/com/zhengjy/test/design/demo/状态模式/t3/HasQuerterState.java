package com.zhengjy.test.design.demo.状态模式.t3;

import java.util.Random;

/**
 * Created by zhengjy on 2019/5/29.
 * 存在硬币状态
 * (HasQuerterState)投入硬币操作-> (soldState)售出糖果
 */
public class HasQuerterState  implements State{
    Random random = new Random(System.currentTimeMillis());
    private GumballMachine gumballMachine;
    public HasQuerterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("已经投入硬币，无需重复投入");

    }

    @Override
    public void ejectQuarter() {
        System.out.println("退出顾客的钱，并将状态转换到NoQuarterState(未投入硬币状态)");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("曲柄转动");
        int winner = random.nextInt(10);
        //如果赢了话，可以让得到两次糖果
        if (winner == 0 && gumballMachine.getCount() > 0){
            //当曲柄转动，将机器的状态转到SoldState(发放糖果状态)
            gumballMachine.setState(gumballMachine.getWinnerState());
        }else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }


    }

    @Override
    public void dispense() {
        System.out.println("次动作不恰当，无需调用方法。曲柄转动时，自动发放。");

    }
}
