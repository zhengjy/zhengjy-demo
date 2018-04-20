package com.zhengjy.test.design.singleton;
/**
 * 单例模式
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午4:29:09
 */
public class Singleton {
	//创建单例的过程可能比较慢
	private Singleton(){
		System.out.println(" new Singlrton");
	}
	private static Singleton singleton = new Singleton();
	
	public static Singleton getSingleton(){
		return singleton;
	}
	
	//这是模拟单例类扮演其他角色
	public static void createString(){
		System.out.println("createString in singleton");
	}
	
	public static void main(String[] args) {
		Singleton.createString();
	}




	public static Singleton Instance = null;



	public static Singleton getInstance(){
		if(Instance==null){
			synchronized (Singleton.class) {
				if (Instance == null) {
					Instance = new Singleton();
				}
			}
		}
		return Instance;
	}
}


