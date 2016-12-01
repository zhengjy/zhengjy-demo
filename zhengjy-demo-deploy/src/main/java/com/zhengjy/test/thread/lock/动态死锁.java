package com.zhengjy.test.thread.lock;
/**
 * 如果有两个线程同时调用transferMoney，其中一个线程从X向Y转账，另一个线程从Y向X转账，那么就会发生死锁。
 * A：transferMoney(myAccount,yourAccount,10)
 * B：transferMoney(yourAccount,myAccount,20)
 * 如果执行顺序不当，那么A可能获得myAccount锁并等待yourAccount的锁，然而B持有yourAccount锁，并正在等待myAccount的锁。
 * <br/>
 * Author ：zhengjy <br/>
 * Create Time：2016年9月24日 下午4:20:36 <br/>
 */
public class 动态死锁 {
	static{
	}
	public void transferMoney(String formAccount,String toAccount,String amount){
		synchronized(formAccount){
			synchronized(toAccount){
				boolean f = false;
				if(f){
					System.out.println("异常");
				}else{
					System.out.println("执行代码快");
				}
			}
		}
	}
}
