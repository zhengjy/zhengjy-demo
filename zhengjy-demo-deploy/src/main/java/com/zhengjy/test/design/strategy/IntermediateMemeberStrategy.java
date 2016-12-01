package com.zhengjy.test.design.strategy;

public class IntermediateMemeberStrategy implements MemberStrategy{

	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("对于中级会员的折扣为10%");
		return booksPrice *0.9;
	}

}
