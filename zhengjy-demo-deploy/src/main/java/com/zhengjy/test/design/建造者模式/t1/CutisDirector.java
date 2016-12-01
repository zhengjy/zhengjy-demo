package com.zhengjy.test.design.建造者模式.t1;

public class CutisDirector {
	public Cutis constructCutis(CutisInterface cutis){
		cutis.getBackgroud();
		cutis.getPigment();
		cutis.getTable();
		return cutis.getCutis();
	}
}
