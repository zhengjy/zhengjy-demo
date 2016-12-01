package com.zhengjy.test.design.factory.t3;

import com.zhengjy.test.design.factory.MainSender;
import com.zhengjy.test.design.factory.Sender;
import com.zhengjy.test.design.factory.SmsSender;

/**
 * 静态工厂模式：将多个工厂模式的方法设置未静态，不需要创建实例
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 上午10:27:34 <br/>
 */
public class StaticSendFactory {
	public static Sender produceMain(){
		return new MainSender();
	}
	
	public static Sender produceSms(){
		return new SmsSender();
	}
}
