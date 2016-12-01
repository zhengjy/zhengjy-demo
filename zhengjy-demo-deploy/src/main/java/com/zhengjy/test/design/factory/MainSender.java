package com.zhengjy.test.design.factory;

public class MainSender implements Sender {

	@Override
	public void send() {
		System.out.println("this is MainSender send()");
	}

}
