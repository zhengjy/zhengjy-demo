package com.zhengjy.test.design.factory;

public class Test {
	public static void main(String[] args) {
		SendFactory sf = new SendFactory();
		Sender s = sf.produce("sms");
		s.send();
		
	}
}
