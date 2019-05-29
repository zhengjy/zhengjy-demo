package com.zhengjy.test.design.demo.状态模式.t3;

/**
 * Created by zhengjy on 2019/5/29.
 */
public class Test {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);
//        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println("当前糖果剩余"+gumballMachine.getCount());
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println("当前糖果剩余"+gumballMachine.getCount());

    }
}
