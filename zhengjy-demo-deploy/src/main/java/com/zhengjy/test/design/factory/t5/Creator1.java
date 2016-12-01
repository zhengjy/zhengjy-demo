package com.zhengjy.test.design.factory.t5;

public class Creator1 extends AbstractCreator{

	@Override
	public AddStockFactory create1() {
		return new HdAdd();
	}

}
