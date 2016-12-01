package com.zhengjy.test.design.observer;

/**
 * 抽象观察者角色：
 * 		把所有的观察者对象的引用保存到一个集合中，每个抽象主题角色都可以有任意数量的观察者。抽象主题提供一个借口，
 * 可以增加删除观察者角色。一般用一个抽象类和接口。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午4:46:46
 */
public interface Watcher {
	void update(String str);
}
