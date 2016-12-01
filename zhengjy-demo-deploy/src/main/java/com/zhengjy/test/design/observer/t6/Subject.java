package com.zhengjy.test.design.observer.t6;

public interface Subject {
	void registerObserver(Observer ovserver);
	void removeObserver(Observer ovserver);
	void notifyObservers();
}
