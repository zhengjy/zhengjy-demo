package com.zhengjy.test.design.demo.状态模式.t1;

/**
 * Created by Administrator on 2016/11/19.
 */
public class NoQuarteState2 extends  StateWrapper{
    GumballMachine2 gumballMachine2;
    public  NoQuarteState2(GumballMachine2 gumballMachine2){
        this.gumballMachine2 = gumballMachine2;
    }

    public  void insertQuarter(){
        System.out.println("NoQuarterState.insertQuarter() 投入25分钱.....执行转动|||||||||||");
        gumballMachine2.setState(gumballMachine2.getNoQuarteState2());
    }
}
