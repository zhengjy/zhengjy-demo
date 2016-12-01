package com.zhengjy.test.design.singleton;
/***
 * 实现了延迟加载的功能，但是引入了同步关键字，因此在多线程环境下，非常耗时。
 * 
 * 延迟加载的核心思想：如果当前并没有使用这个组件，则不需要真正的初始化它，使一个代理对象替代它原有的位置，要是
 * 真正要使用的时候，才对它进行加载。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午1:37:27
 */
public class LazySingleton {
	private LazySingleton(){
		System.out.println("LazySingeton is create");
	}
	
	private static LazySingleton instance = null;
	
	public static synchronized LazySingleton getLazySingleton(){
		if(instance == null){
			instance  = new LazySingleton();
		}
			return instance;
	}
}
