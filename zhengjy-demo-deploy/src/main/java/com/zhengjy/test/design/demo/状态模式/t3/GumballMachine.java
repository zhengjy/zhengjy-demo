package com.zhengjy.test.design.demo.状态模式.t3;

import java.util.Random;

/**
 * Created by zhengjy on 2019/5/29.
 * 糖果机
 */
public class GumballMachine {


    //售馨状态
    private State soldOutState;
    //没有投入硬币
    private State noQuarterState;
    //存在硬币
    private State hasQuarterState;
    //发放糖果
    private State soldState;

    private State winnerState;
    //当前实例变量
    private State state;
    //记录机器内装多少糖果，开始机器是没有装糖果
    private int count;

    public GumballMachine(int count){
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuerterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);
        this.count = count;
        state = noQuarterState;
    }

    //放入硬币
    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();

    }

    /**
     * 不需要再GumballMachine准备dispense动作方法，因为这是一个内部的动作，
     * 用户不能直接要求机器方法糖果，当转动曲柄时，可直接发放糖果
     */
    public void turnCrank() {


        state.turnCrank();
        state.dispense();

    }

    /**
     * 这个方法允许其他对象(像我们的状态对象)将机器的状态转换到不同的状态
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall(){
        System.out.println("辅助方法来释放糖果，将count实例变量的值减1");
        if (count != 0){
            count = count - 1;
        }

    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(State soldOutState) {
        this.soldOutState = soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public void setNoQuarterState(State noQuarterState) {
        this.noQuarterState = noQuarterState;
    }

    public void setHasQuarterState(State hasQuarterState) {
        this.hasQuarterState = hasQuarterState;
    }


    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public void setSoldState(State soldState) {
        this.soldState = soldState;
    }

    public State getState() {
        return state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public void setWinnerState(State winnerState) {
        this.winnerState = winnerState;
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "soldOutState=" + soldOutState +
                ", noQuarterState=" + noQuarterState +
                ", hasQuarterState=" + hasQuarterState +
                ", soldState=" + soldState +
                ", winnerState=" + winnerState +
                ", state=" + state +
                ", count=" + count +
                '}';
    }
}
