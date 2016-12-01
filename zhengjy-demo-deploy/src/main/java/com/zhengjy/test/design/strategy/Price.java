package com.zhengjy.test.design.strategy;

public class Price {
	//持有一个具体的策略对象
	private MemberStrategy strategy;
	
	public Price(MemberStrategy strategy){
		this.strategy = strategy;
	}
	
	/*
	 * 计算图示的价格
	 */
	public double quote(double booksPrice){
		return this.strategy.calcPrice(booksPrice);
	}
}
