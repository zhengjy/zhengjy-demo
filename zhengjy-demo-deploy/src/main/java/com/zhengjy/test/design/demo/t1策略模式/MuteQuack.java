package com.zhengjy.test.design.demo.t1策略模式;

public class MuteQuack implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("无声的鸭子");
	}

}
