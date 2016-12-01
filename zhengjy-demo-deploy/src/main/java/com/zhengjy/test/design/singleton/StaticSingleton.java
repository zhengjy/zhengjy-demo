package com.zhengjy.test.design.singleton;
/**
 * 在这个实现中，单例模式使用了内部类来维护单例的实例，当StaticSingleton被加载时，其内部类并不会被初始化，故可以确保当
 * StaticSingleton类被JVM加载时，不会初始化单例类，当getInstance()方法被调用时，才会加载SingletonHolder，从而初始化instance。
 * 同时，由于实例的建立是类加载时完成，故天生对多线程友好，getInstance()方法也不需要使用同步关键字。
 *
 * 使用内部类的方法实现单例，即可以做到延迟加载，也不必使用关键字，是一种比较完善的实现。
 * 
 *
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午1:40:21
 */
public class StaticSingleton {
	private StaticSingleton(){
		System.out.println("StaticSingleton is create");
	}
	
	private static class SingletonHolder{
		public static StaticSingleton instance = new StaticSingleton();
	}
	
	public static StaticSingleton getInstance(){
		return SingletonHolder.instance;
	}
	
	public static void main(String[] args) {
		StaticSingleton ss = new StaticSingleton();
		SingletonHolder s = new SingletonHolder();
		
		System.out.println(ss.getInstance().equals(s.instance));
	}
}
