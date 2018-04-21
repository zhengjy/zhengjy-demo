package com.zhengjy.test.design.singleton.t2;
/**
 * 单例模式
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午4:29:09
 */
public class Singleton {
	private Singleton(){}
	public Singleton get(){
		return Child.singleton;
	}

	public static void main(String[] args) {
		System.currentTimeMillis();
	}


	static class Child{
		private Child(){}
		static Singleton singleton = new Singleton();
	}
}
