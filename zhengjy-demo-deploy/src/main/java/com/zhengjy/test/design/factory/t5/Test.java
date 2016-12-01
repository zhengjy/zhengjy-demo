package com.zhengjy.test.design.factory.t5;

public class Test {
	public static void main(String[] args) {
		/*AbstractCreator a = new Creator1();
		AddStockFactory as =a.create1();
		as.add("xxx");
		as.select("222");*/
		
		HdAdd as = new HdAdd();
		as.add("xs");
		as.select("x");
	}
}
