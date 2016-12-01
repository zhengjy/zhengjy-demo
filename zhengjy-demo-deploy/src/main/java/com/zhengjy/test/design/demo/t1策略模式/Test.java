package com.zhengjy.test.design.demo.t1策略模式;
/**
 * 策略模式：
 * Author ：zhengjy <br/>
 * Create Time：2016年11月3日 上午7:56:32 <br/>
 */
public class Test {
	public static void main(String[] args) {
		FlyBehavior flyBehavior = new FlyWithWings();
		QuackBehavior  quackBehavior = new MuteQuack();
		Duck duck = new MallardDuck(flyBehavior,quackBehavior);
		duck.performFly();
		duck.performQuack();
	}
}
