package com.zhengjy.test.design.demo.组合模式;

/**
 * Created by zhengjy on 2016/11/20.
 */
public class QuackDecorator implements Quackable {
    Quackable quackable;
    static int count=0;
    public  QuackDecorator(Quackable quackable){
        this.quackable = quackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        count++;
    }

    public  static  int getCount(){
        return  count;
    }
}
