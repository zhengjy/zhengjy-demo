package com.zhengjy.test.design.duty_chain责任链.t3;

public class Test {
	public static void main(String[] args) {
		Hanndler3 h1 = new UserWhite();
		Hanndler3 h2 = new IpWhile();
		Hanndler3 h3 = new UserBlack();
		Hanndler3 h4 = new IpBlack();
		h1.setHanndler(h2);
		h2.setHanndler(h3);
		h3.setHanndler(h4);
		User u = new User();
		u.setIp("127.0.0.01");
		u.setName("s");
		boolean b = h1.hanndlerRequest(u);
		System.out.println(b);
		
	}
}
