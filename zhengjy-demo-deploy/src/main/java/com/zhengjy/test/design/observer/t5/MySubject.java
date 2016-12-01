package com.zhengjy.test.design.observer.t5;

public class MySubject extends AbstractSubject{

	@Override
	public void operation() {
		System.out.println("MySubject operation!");
		notifyObservers();
	}

}
