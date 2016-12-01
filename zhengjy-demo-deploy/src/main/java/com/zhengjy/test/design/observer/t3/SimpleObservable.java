package com.zhengjy.test.design.observer.t3;

import java.util.Observable;

/**
 * 被观察者
 * @author zhengjy
 * @version 一
 * @date 2016年6月11日 下午1:50:47
 */
public class SimpleObservable extends Observable {
	private int  data =0;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		if(this.data != data){
			this.data = data;
			setChanged();
			
			notifyObservers();//只有在setChanged()被调用后，notifyObservers()才会去调用update()，否则什么都不干
		}
		
		
	}
	
	
}
