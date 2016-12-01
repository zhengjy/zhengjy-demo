package com.zhengjy.test.design.observer.t3;

public class Test {
	public static void main(String[] args) {
		 SimpleObservable doc = new SimpleObservable();
	      SimpleObServer view = new SimpleObServer(doc);
	      doc.setData(1);    
	      doc.setData(2);    
	      doc.setData(2);    
	      doc.setData(3);   
	}
}
