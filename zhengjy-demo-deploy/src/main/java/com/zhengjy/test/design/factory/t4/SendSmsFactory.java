package com.zhengjy.test.design.factory.t4;

import com.zhengjy.test.design.factory.Sender;
import com.zhengjy.test.design.factory.SmsSender;

public class SendSmsFactory implements Provider {

	@Override
	public Sender produce() {
		return new SmsSender();
	}

}
