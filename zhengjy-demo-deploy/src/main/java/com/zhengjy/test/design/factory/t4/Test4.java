package com.zhengjy.test.design.factory.t4;

import com.zhengjy.test.design.factory.Sender;

/**
 * 工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则，
 * 所以，从设计角度考虑，有一定的问题。就用到抽象工厂模式，创建多个工厂类，这样一旦需要新增新的功能，直接增加新的工厂类就可以了。
 * 不需要修改以前的代码。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 上午11:11:37 <br/>
 */
public class Test4 {
	public static void main(String[] args) {
		Provider provider = new SendMainFactory();
		Sender s = provider.produce();
		s.send();
	}
}
