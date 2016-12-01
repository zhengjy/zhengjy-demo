package com.zhengjy.test.design.observer;

import java.util.Vector;

/**
 * 具体主题角色：
 * 		在具体主题内部状态改变时，给所有登记过的观察者发通知。具体主题角色通常用一个子类实现。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午4:53:57
 */
public class ConcreteWatched implements Watched{
	private Vector<Watcher> list = new Vector<Watcher>();//存放观察者
	@Override
	public void addWatched(Watcher watcher) {
		list.add(watcher);
		
	}

	@Override
	public void removeWatched(Watcher watcher) {
		list.removeElement(watcher);
	}

	@Override
	public void notifyWatched(String str) {
		for(Watcher w:list){
			w.update(str);
		}
	}
	
}
