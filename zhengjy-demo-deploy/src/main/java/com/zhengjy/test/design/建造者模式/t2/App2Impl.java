package com.zhengjy.test.design.建造者模式.t2;

public class App2Impl extends CutisInterface{
	
	public App2Impl(Cutis cutis) {
		super(cutis);
	}

	public void banner() {
		super.cutis.setBanner("App2 cutis add Banner");
	}

	public void icon() {
		super.cutis.setIcon("App2 cutis add icon...");
	}

	public void showcase() {
		super.cutis.setShowcase("App2 cutis add showcase");
	}

	public void goods() {
		super.cutis.setGoods("App2 cutis add goods .....");
	}

	public Cutis getCutis() {
		return super.cutis;
	}

}
