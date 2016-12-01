package com.zhengjy.test.design.strategy;


public interface MemberStrategy {
	/**
	 * 计算图示的价格
	 * @author zhengjy
	 * @param booksPrice
	 * @return
	 */
	public double calcPrice(double booksPrice);
}
