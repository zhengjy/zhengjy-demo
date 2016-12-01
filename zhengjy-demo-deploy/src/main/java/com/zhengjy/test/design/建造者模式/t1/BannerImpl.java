package com.zhengjy.test.design.建造者模式.t1;

public class BannerImpl implements CutisInterface{
	private Cutis cutis=null;
	public BannerImpl(){
		this.cutis = new Icon();
	}
	
	@Override
	public void getBackgroud() {
		cutis.setBackgroud("Banner加上背景");
		
	}

	@Override
	public void getPigment() {
		cutis.setPigment("Banner加上颜色pigment");
	}

	@Override
	public void getTable() {
		cutis.setTable("Banner加上表格");
	}

	@Override
	public Cutis getCutis() {
		return cutis;
	}

}
