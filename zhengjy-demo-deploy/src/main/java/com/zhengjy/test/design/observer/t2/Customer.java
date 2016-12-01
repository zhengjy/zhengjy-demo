package com.zhengjy.test.design.observer.t2;

import java.util.Vector;

/**
 * 具体主题，客户
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午5:18:49
 */
public class Customer implements ISubject{
	private String customerState;
	private Vector<JobStation> observers = new Vector<JobStation>();
	
	
	
	@Override
	public void Notify() {
		// TODO Auto-generated method stub
		
	}
}	
