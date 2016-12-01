package com.zhengjy.test.design.Bridge.t2;

public abstract class AbstractNoodle {
	Peppery peppery;
	
	public AbstractNoodle(Peppery peppery){
		this.peppery = peppery;
	}
	public abstract void eat();
}
