package com.zhengjy.test.design.proxy.t1;

public class Proxy implements Sourceable{
	private Source source;
	public Proxy(){
		super();
		this.source = new Source();
	}
	
	@Override
	public void method() {
		before();
		source.method();
		atfer();
	}
	
	private void atfer(){
		System.out.println("atfer proxy");
	}
	
	private void before(){
		System.out.println("before proxy");
	}
}
