package com.zhengjy.test.design.demo.状态模式.t1;

/**
 * Created by Administrator on 2016/11/19.
 */
public class HasQuarterState2 extends StateWrapper{
    GumballMachine2 gumballMachine2;
    public  HasQuarterState2(GumballMachine2 gumballMachine2){
        this.gumballMachine2 = gumballMachine2;
    }
}
