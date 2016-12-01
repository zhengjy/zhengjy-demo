package com.zhengjy.test.design.factory.t3;

import com.zhengjy.test.design.factory.Sender;

public class Test {
	public static void main(String[] args) {
		Sender s = StaticSendFactory.produceSms();
		s.send();
	}
}
