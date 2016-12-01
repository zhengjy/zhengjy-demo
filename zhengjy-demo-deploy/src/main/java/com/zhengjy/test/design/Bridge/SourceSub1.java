package com.zhengjy.test.design.Bridge;

public class SourceSub1 implements Sourceable {
	@Override
	public void method() {
		System.out.println("SourceSub1 method()");
	}

}
