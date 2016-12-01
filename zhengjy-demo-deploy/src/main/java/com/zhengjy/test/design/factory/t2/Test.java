package com.zhengjy.test.design.factory.t2;

import com.zhengjy.test.design.factory.Sender;

public class Test {
	public static void main(String[] args) {
		ManySendFactory sf= new ManySendFactory();
		Sender s = sf.produceSms();
		s.send();
	}
}
