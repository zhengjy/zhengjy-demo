package com.zhengjy.test.design.demo.t1策略模式;

public class Sequak implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("尖叫的鸭子");
	}

}
