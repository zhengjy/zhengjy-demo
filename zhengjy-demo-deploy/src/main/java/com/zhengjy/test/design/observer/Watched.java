package com.zhengjy.test.design.observer;
/**
 * 抽象主题角色，watched被观察者
 *		为所有具体的观察者定义一个接口，在得到主题的通知时更新自己。 
 *
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午4:49:37
 */
public interface Watched {
	void addWatched(Watcher watcher);
	void removeWatched(Watcher watcher);
	void notifyWatched(String str);
}	
