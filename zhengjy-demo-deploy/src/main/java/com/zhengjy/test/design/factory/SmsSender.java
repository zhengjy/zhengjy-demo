package com.zhengjy.test.design.factory;

public class SmsSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is SmsSender send()");
	}

}
