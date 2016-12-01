package com.zhengjy.test.design.observer.t3;

import java.util.Observable;
import java.util.Observer;

public class SimpleObServer  implements Observer{
	 public SimpleObServer(SimpleObservable simpleObservable){    
		//通过生成被观察者实例（SimpleObservable）的实例，来调用addObserver(this)方法让观察者(SimpleObServer)达到观察者观察被观察者目的
	      simpleObservable.addObserver(this); 
	   }  
	@Override
	public void update(Observable o, Object data) {//data为任意对象，用于传递对象
		System.out.println("数据变动： "+((SimpleObservable) o).getData());
	}

}
