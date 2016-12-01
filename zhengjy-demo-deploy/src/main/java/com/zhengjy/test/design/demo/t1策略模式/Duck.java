package com.zhengjy.test.design.demo.t1策略模式;

public abstract class Duck {
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior){
		this.flyBehavior = flyBehavior;
		this.quackBehavior = quackBehavior;
	}
	
	abstract void display();
	
	
	public void performFly(){
		flyBehavior.fiy();
	}
	
	public void performQuack(){
		quackBehavior.quack();
	}
}
 