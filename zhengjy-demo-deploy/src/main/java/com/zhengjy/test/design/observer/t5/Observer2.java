package com.zhengjy.test.design.observer.t5;


public class Observer2 implements ObServer {

	@Override
	public void update() {
		System.out.println("Observer2 update()");
	}

}
