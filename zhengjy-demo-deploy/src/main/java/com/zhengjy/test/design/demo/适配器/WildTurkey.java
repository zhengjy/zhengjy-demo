package com.zhengjy.test.design.demo.适配器;

public class WildTurkey implements Turkey{

	@Override
	public void gobble() {
		System.out.println("WildTurkey.gobble()");
	}

	@Override
	public void fly() {
		System.out.println("WildTurkey.fly()");
	}

}
