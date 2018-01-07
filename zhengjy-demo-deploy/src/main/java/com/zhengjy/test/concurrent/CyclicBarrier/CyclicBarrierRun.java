package com.zhengjy.test.concurrent.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierRun implements Runnable{
	CyclicBarrier cyclicBarrier1 = null;
	CyclicBarrier cyclicBarrier2 = null;
	
	public CyclicBarrierRun(CyclicBarrier cyclicBarrier1,CyclicBarrier cyclicBarrier2){
		this.cyclicBarrier1 = cyclicBarrier1;
		this.cyclicBarrier2 = cyclicBarrier2;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+ " cyclicBarrier1");
			cyclicBarrier1.await();
			
			
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+ " cyclicBarrier2");
			cyclicBarrier2.await();
			
			
			System.out.println(Thread.currentThread().getName()+ " done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
