package com.zhengjy.test.design.建造者模式.t2;

public class App1Impl extends CutisInterface {
	
	public App1Impl(Cutis cutis) {
		super(cutis);
	}

	public void banner() {
		super.cutis.setBanner("App1 cutis add Banner");
	}

	public void icon() {
		super.cutis.setIcon("App1 cutis add icon...");
	}

	public void showcase() {
		super.cutis.setShowcase("App1 cutis add showcase");
	}

	public void goods() {
		super.cutis.setGoods("App1 cutis add goods .....");
	}

	public Cutis getCutis() {
		return super.cutis;
	}

}
