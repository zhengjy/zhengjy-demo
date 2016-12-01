package com.zhengjy.test.design.factory.t2;

import com.zhengjy.test.design.factory.MainSender;
import com.zhengjy.test.design.factory.Sender;
import com.zhengjy.test.design.factory.SmsSender;

/**
 * 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正常创建对象
 * 而多个工厂方法模式是提供多个工厂方法，分别创建对象
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 上午10:27:34 <br/>
 */
public class ManySendFactory {
	public Sender produceMain(){
		return new MainSender();
	}
	
	public Sender produceSms(){
		return new SmsSender();
	}
}
