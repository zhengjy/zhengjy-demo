package com.zhengjy.test.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量：Semaphore可以很轻松的完成信号量控制，semaphore可以控制每个资源 被同时访问的个数.
 * 	acquire()获取一个许可。
 *  release()释放一个许可。
 *  availablePermits()信号灯库中有多个可以被使用
 * <br/>
 * 
 * Author ：zhengjy <br/>
 * Create Time：2016年9月29日 上午9:09:38 <br/>
 */
public class SemapHore1 extends Thread{
	private int id;
	private Semaphore semaphore;
	public SemapHore1(int i,Semaphore semaphore){
		this.id =i;
		this.semaphore=semaphore;
	}
	
	@Override
	public void run() {
		//
		if(semaphore.availablePermits() >0){
			System.out.println("顾客 +["+this.id+"]，进入厕所，有空位");
		}else{
			System.out.println("顾客 +["+this.id+"]，进入厕所，没空位，排队");
		}
		try {
			semaphore.acquire();//获取一个许可。
			System.out.println("顾客 +["+this.id+"]，获得坑位");
			Thread.sleep(3000);
			System.out.println("顾客 +["+this.id+"]，使用完毕");
			semaphore.release();//释放一个许可。
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 这里是一个实际的情况，大家排队上厕所，厕所只有两个位置，来了10个人需要排队。1
	 */
	public static void main(String[] args) {
		ExecutorService executor  = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(2);//只有两个厕所
		for(int i=0;i<10;i++){
			executor.submit(new SemapHore1(i+1, semaphore));
		}
		executor.shutdown();
		semaphore.acquireUninterruptibly(2);
		System.out.println("使用完毕，需要清扫了");
		semaphore.release(2);
		
		
	}
	
	
}
