package com.zhengjy.test.design.建造者模式.t2;

public abstract class CutisInterface {
	Cutis cutis = null;
	public CutisInterface(Cutis cutis){
		this.cutis = cutis;
	}
	public abstract void banner();
	abstract void icon();
	abstract void showcase();
	abstract void goods();
	Cutis getCutis(){
		 return cutis;
	};
}
