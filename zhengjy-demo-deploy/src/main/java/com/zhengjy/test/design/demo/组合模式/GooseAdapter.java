package com.zhengjy.test.design.demo.组合模式;

/**
 * Created by zhengjy on 2016/11/20.
 * 适配器模式，不用新建类，改变行为
 */
public class GooseAdapter implements Quackable {
    Goose goose;
    public  GooseAdapter(Goose goose){
        this.goose = goose;
    }

    public void quack(){
        goose.honk();
    }
}
