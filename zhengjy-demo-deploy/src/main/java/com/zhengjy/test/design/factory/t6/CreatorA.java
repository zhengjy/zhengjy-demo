package com.zhengjy.test.design.factory.t6;

public class CreatorA extends AbstractCretorA {

	@Override
	public AbstractProductA createProductA() {
		return new ProductA();
	}

	@Override
	public AbstractProductA createProcuctB() {
		return new ProductB();
	}

	

}
