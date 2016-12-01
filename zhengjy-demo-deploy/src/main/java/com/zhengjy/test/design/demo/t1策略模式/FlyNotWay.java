package com.zhengjy.test.design.demo.t1策略模式;

public class FlyNotWay implements FlyBehavior {

	@Override
	public void fiy() {
		System.out.println("不会飞的鸭子");
	}

}
