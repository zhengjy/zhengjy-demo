package com.zhengjy.test.design.demo.状态模式;

/**
 * Created by Administrator on 2016/11/19.
 */
public class GumballMachine {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State winnerState;
    State soldState;

    State state = soldOutState;//实例变量，允许其他的对象将机器的状态转换不同的状态
    int count=0;//记录机器装有多少个糖果

    public GumballMachine(int numberGumballs){
        // 每种状态都创建一个状态实例
        soldOutState=new SoldOutState(this);
        noQuarterState=new NoQuarterState(this);
        hasQuarterState=new HasQuarterState(this);
        soldState=new SoldState(this);
        winnerState = new WinnerState(this);

        this.count = numberGumballs;
        // 若超过0颗糖果，就将状态设置为NoQuarterState
        if(numberGumballs > 0) {
            state = noQuarterState;
        }
    }

    public void  insertQuarter(){
        state.insertQuarter();
    }

    public void ejectQuarter(){
        state.ejectQuarter();
    }
    public  void turnCrank(){
        state.turnCrank();
        state.dispense();
    }


    public void setState(State state){
        this.state = state;
    }

    public  void releaseBall(){//减库存
        System.out.println("");
        if (count !=0){
            count --;
        }
    }

    public State getWinnerState() {
        return winnerState;
    }

    public void setWinnerState(State winnerState) {
        this.winnerState = winnerState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
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

    public State getSoldState() {
        return soldState;
    }

    public void setSoldState(State soldState) {
        this.soldState = soldState;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
