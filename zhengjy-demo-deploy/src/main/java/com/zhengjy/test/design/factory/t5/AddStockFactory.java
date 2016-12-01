package com.zhengjy.test.design.factory.t5;

public abstract class AddStockFactory implements AddStockInterface {

	public abstract void add(String a);
	public String select(String type){
		return type;
		
	}
}
