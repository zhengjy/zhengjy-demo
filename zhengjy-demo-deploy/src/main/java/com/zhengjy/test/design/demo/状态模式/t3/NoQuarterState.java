package com.zhengjy.test.design.demo.状态模式.t3;

/**
 * Created by zhengjy on 2019/5/29.
 * 未投入状态
 */
public class NoQuarterState implements State {
    private GumballMachine gumballMachine;

    /**
     * 通过构造器得到糖果机的引用，然后将它记录在实例变量
     * @param gumballMachine
     */
    public NoQuarterState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("我们接受了25分钱，然后改变机器的状态到HasQuarterState");
        //投入硬币之后，将状态转入到HasQuarterState(已经投入状态)
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("你还没有投入25分钱，不能退钱");
    }

    @Override
    public void turnCrank() {
        System.out.println("你还没有投钱，不能要求糖果");
    }

    @Override
    public void dispense() {
        System.out.println("你还没有投钱，不能发放糖果");

    }
}
