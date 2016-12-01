package com.zhengjy.test.design.proxy.t1;

public class Source implements Sourceable {

	@Override
	public void method() {
		System.out.println("this is Source method()");
	}

}
