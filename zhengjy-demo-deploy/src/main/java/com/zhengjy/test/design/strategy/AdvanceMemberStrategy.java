package com.zhengjy.test.design.strategy;

public class AdvanceMemberStrategy implements MemberStrategy{

	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
	}

}
