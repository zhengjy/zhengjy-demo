package com.zhengjy.test.design.adapter;
/**
 * 适配器模式：类的适配器
 * 
 * 有一个Source方法，拥有一个方法，带适配，目标接口Targetable，通过Adapter()类，将Source的功能扩展到Targetable接口里。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 上午11:32:51 <br/>
 */
public class Test1 {
	public static void main(String[] args) {
		Targetable targetable = new Adapter();
		targetable.method1();
		targetable.method2();
	}
}
