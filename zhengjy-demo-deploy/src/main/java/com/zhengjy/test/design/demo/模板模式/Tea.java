package com.zhengjy.test.design.demo.模板模式;

public class Tea extends CaffeineBeverage{

	@Override
	void brew() {
		System.out.println("Tea brew");
	}

	@Override
	void addCondiments() {
		System.out.println("Tea addCondiments");
	}

}
