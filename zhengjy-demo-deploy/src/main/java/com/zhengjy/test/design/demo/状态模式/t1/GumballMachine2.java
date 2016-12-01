package com.zhengjy.test.design.demo.状态模式.t1;

import com.zhengjy.test.design.demo.状态模式.State;

/**
 * Created by Administrator on 2016/11/19.
 */
public class GumballMachine2 {
    State noQuarteState2;
    State hasQuarterState2;
    State soldState2;
    State soldOutState2;

    private State state =noQuarteState2;

    int conut =0;

    public GumballMachine2(int numberGumballs){
        // 每种状态都创建一个状态实例
        noQuarteState2=new NoQuarteState2(this);
        hasQuarterState2=new HasQuarterState2(this);
        soldOutState2=new SoldOutState2(this);
        soldState2=new SoldState2(this);
//        winnerState = new WinnerState(this);

        this.conut = numberGumballs;
        // 若超过0颗糖果，就将状态设置为NoQuarterState
        if(numberGumballs > 0) {
            state = noQuarteState2;
        }
    }

    public  void  setState(State state){
        this.state =state;
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

    public void relese(){
        if (conut>0){
            conut--;
        }
    }

    public State getNoQuarteState2() {
        return noQuarteState2;
    }

    public void setNoQuarteState2(State noQuarteState2) {
        this.noQuarteState2 = noQuarteState2;
    }

    public State getHasQuarterState2() {
        return hasQuarterState2;
    }

    public void setHasQuarterState2(State hasQuarterState2) {
        this.hasQuarterState2 = hasQuarterState2;
    }

    public State getSoldState2() {
        return soldState2;
    }

    public void setSoldState2(State soldState2) {
        this.soldState2 = soldState2;
    }

    public State getSoldOutState2() {
        return soldOutState2;
    }

    public void setSoldOutState2(State soldOutState2) {
        this.soldOutState2 = soldOutState2;
    }

    public int getConut() {
        return conut;
    }

    public void setConut(int conut) {
        this.conut = conut;
    }
}
