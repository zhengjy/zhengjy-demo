package com.zhengjy.test.design.factory.t4;

import com.zhengjy.test.design.factory.MainSender;
import com.zhengjy.test.design.factory.Sender;

public class SendMainFactory implements Provider{

	@Override
	public Sender produce() {
		return new MainSender();
	}

}
