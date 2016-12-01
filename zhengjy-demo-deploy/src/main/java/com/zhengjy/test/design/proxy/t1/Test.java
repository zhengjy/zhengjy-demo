package com.zhengjy.test.design.proxy.t1;

public class Test {
	public static void main(String[] args) {
		Sourceable s =new Proxy();
		s.method();
	}
}
