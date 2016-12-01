package com.zhengjy.test.design.factory;
/**
 * 普通工厂：就是建立一个工厂类，对实例了同一个接口的一些类进行实例的创建。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 上午10:16:49 <br/>
 */
public class SendFactory {
	public Sender produce(String type){
		if("main".equals(type)){
			return new MainSender();
		}else if("sms".equals(type)){
			return new SmsSender();
		}
		return null;
		
	}
}
