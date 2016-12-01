package com.zhengjy.test.design.建造者模式.t1;

public class IconImpl implements CutisInterface{
	private Cutis cutis=null;
	public IconImpl(){
		this.cutis = new Icon();
	}
	
	@Override
	public void getBackgroud() {
		cutis.setBackgroud("图标加上背景");
		
	}

	@Override
	public void getPigment() {
		cutis.setPigment("图标加上颜色pigment");
	}

	@Override
	public void getTable() {
		cutis.setTable("图标加上表格");
	}

	@Override
	public Cutis getCutis() {
		return cutis;
	}

}
