package com.zhengjy.test.design.demo.t1策略模式;

public class FlyWithWings implements FlyBehavior {

	@Override
	public void fiy() {
		System.out.println("会飞的鸭子");
	}

}
