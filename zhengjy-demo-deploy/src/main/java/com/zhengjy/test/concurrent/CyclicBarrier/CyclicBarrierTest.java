package com.zhengjy.test.concurrent.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier：CyclicBarrier强调的是n个线程，大家互相等待，只要一个没完成，所有人等待。
 * 栅栏类似闭锁，但是他们是有区别的
 * 闭锁用来等待事件，而栅栏用于等待其他线程。
 * 
 * 
 * @author zhengjy
 *
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		Runnable barrier =new Runnable() {
			@Override
			public void run() {
				System.out.println("execute barrier1");
			}
		};
		
		Runnable barrier2 =new Runnable() {
			@Override
			public void run() {
				System.out.println("execute barrier2");
			}
		};
		CyclicBarrier cyclicBarrier1 =  new CyclicBarrier(2, barrier);
		CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2,barrier2);
		
		CyclicBarrierRun cyclircBarrierRun1 = new CyclicBarrierRun(cyclicBarrier1, cyclicBarrier2);
		CyclicBarrierRun cyclircBarrierRun2 = new CyclicBarrierRun(cyclicBarrier1, cyclicBarrier2);
		
		new Thread(cyclircBarrierRun1).start();
		new Thread(cyclircBarrierRun2).start();
		
	}
}
