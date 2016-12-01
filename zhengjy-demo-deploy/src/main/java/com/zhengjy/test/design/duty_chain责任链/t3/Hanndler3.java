package com.zhengjy.test.design.duty_chain责任链.t3;

public abstract class Hanndler3 {
	protected Hanndler3 hanndler;
	
	public Hanndler3 getHanndler() {
		return hanndler;
	}

	public void setHanndler(Hanndler3 hanndler) {
		this.hanndler = hanndler;
	}

	abstract boolean hanndlerRequest(User u);
}
