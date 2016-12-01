package com.zhengjy.test.design.observer.t5;

public interface Subject {
	public void add(ObServer o);
	
	public void del(ObServer o);
	
	public void notifyObservers();
	
	public void operation();
}
