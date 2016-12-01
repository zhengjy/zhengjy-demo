package com.zhengjy.test.design.demo.状态模式;

/**
 * Created by Administrator on 2016/11/19.
 */
public class Test {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);
        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println("The current gumball number is:" + gumballMachine.getCount());
        System.out.println(gumballMachine);

        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
       // gumballMachine.turnCrank();
        System.out.println("The current gumball number is:" + gumballMachine.getCount());



        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println("The current gumball number is:" + gumballMachine.getCount());
        System.out.println(gumballMachine);
    }
}
