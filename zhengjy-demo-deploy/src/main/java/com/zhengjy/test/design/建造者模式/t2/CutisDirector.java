package com.zhengjy.test.design.建造者模式.t2;

public class CutisDirector {
	CutisInterface ci;
	public void setCutis(CutisInterface ci){
		this.ci =ci;
	}
	
	
	public Cutis getApp1(){
		ci.banner();
		ci.icon();
		ci.showcase();
		ci.goods();
		return ci.getCutis();
	}
	public Cutis getApp2(){
		ci.banner();
		ci.icon();
		ci.showcase();
		//ci.goods();
		return ci.getCutis();
	}
	
}
