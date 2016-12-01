package com.zhengjy.test.design.建造者模式.t1;

public class Test {
	public static void main(String[] args) {
		CutisDirector cutisDirector = new CutisDirector();
		Cutis c = cutisDirector.constructCutis(new IconImpl());
		Cutis c2 = cutisDirector.constructCutis(new BannerImpl());
		System.out.println(c.getBackgroud());
		System.out.println(c.getPigment());
		System.out.println(c.getTable());
		System.out.println(c2.getBackgroud());
		System.out.println(c2.getPigment());
		System.out.println(c2.getTable());
		
	}
}
