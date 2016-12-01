 package com.zhengjy.test.design.observer;
/**
 * 具体观察者角色：
 * 		该角色实现抽象观察者角色所有要更新的接口，以便使本身的状态与主题的状态相协调。通常用一个子类实现。
 * 如果需要，具体观察者角色可以保持一个指向具体主题角色的引用。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午4:51:02
 */
public class ConcreteWatcher implements Watcher{

	@Override
	public void update(String str) {
		System.out.println(str);
	}

}
