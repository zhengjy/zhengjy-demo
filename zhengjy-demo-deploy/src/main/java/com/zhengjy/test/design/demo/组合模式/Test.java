package com.zhengjy.test.design.demo.组合模式;

/**
 * Created by zhengjy on 2016/11/20.
 */
public class Test {
    public static void main(String[] args) {
        Test t  = new Test();
        t.simulate();
    }

    void simulate(){
        Quackable mallardDuck = new QuackDecorator(new MallardDuck());
        Quackable redheadDuck = new QuackDecorator(new RedheadDuck());
        Quackable duckCall = new QuackDecorator(new DuckCall());
        Quackable rubberDuck = new QuackDecorator(new RubberDuck());
        Quackable goose = new QuackDecorator(new GooseAdapter(new Goose()));

        simulate(mallardDuck);
        simulate(redheadDuck);
        simulate(duckCall);
        simulate(rubberDuck);
        simulate(goose);
        System.out.println("count="+QuackDecorator.getCount());
    }

    void  simulate(Quackable quackable){
        quackable.quack();
    }
}
