package com.zhengjy.test.design.factory.t5;

public class HdAdd extends AddStockFactory {

	@Override
	public void add(String s) {
		System.out.println("hd add");
	}

}
