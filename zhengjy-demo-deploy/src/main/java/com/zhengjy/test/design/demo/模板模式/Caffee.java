package com.zhengjy.test.design.demo.模板模式;

public class Caffee extends CaffeineBeverage {

	@Override
	void brew() {
		System.out.println("Coffee brew");
	}

	@Override
	void addCondiments() {
		System.out.println("Caffee addCondiments");
	}

}
