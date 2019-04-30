package com.zhengjy;

/**
 * Created by Jiyang.Zheng on 2019/1/17 16:11.
 */
public class Test {





    public void sayhello(Human guy) {
        System.out.println("Human guy");

    }

    public void sayhello(Man guy) {
        System.out.println("Man guy");

    }

    public void sayhello(Woman guy) {
        System.out.println("Woman guy");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        Test staticDispatch = new Test();
        staticDispatch.sayhello(man);// Human guy
        staticDispatch.sayhello(woman);// Human guy
    }

}

class Human {

}
class Man extends Human {

}

class Woman extends Human {

}