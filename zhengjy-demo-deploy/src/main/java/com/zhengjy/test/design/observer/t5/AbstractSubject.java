package com.zhengjy.test.design.observer.t5;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements Subject {
	private Vector<ObServer> vector =new Vector<ObServer>();
	
	@Override
	public void add(ObServer o) {	
		vector.add(o);
	}

	@Override
	public void del(ObServer o) {
		vector.remove(o);
	}

	@Override
	public void notifyObservers() {
		Enumeration<ObServer> enumo = vector.elements();
		while(enumo.hasMoreElements()){
			enumo.nextElement().update();
		}
		
	}
	
}






