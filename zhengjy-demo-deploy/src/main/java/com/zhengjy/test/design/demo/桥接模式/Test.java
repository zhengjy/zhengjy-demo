package com.zhengjy.test.design.demo.桥接模式;

/**
 * Created by zhengjy on 2017/5/7.
 */
public class Test {
}
interface Implementor {
    public void operationImpl();
}

abstract class Abstraction {
    protected Implementor impl; //定义实现类接口对象

    public void setImpl(Implementor impl) {
        this.impl=impl;
    }

    public abstract void operation();  //声明抽象业务方法
}

class RefinedAbstraction extends Abstraction {
    public void operation() {
        //业务代码
        impl.operationImpl();  //调用实现类的方法
        //业务代码
    }
}