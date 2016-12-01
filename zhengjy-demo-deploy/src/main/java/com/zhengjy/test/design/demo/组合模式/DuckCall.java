package com.zhengjy.test.design.demo.组合模式;

/**
 * Created by zhengjy on 2016/11/20.
 */
public class DuckCall implements Quackable {
    @Override
    public void quack() {
        System.out.println("DuckCall quack()");
    }
}
