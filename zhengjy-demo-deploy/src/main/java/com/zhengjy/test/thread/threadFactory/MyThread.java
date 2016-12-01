package com.zhengjy.test.thread.threadFactory;

public class MyThread implements ThreadFactory {
	private final String poolName;
	public MyThread(String poolName) {
		super();
		this.poolName = poolName;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new MyAppThread(r,poolName) ;
	}
	
	
	public static void main(String[] args) {
		MyThread t = new MyThread("xxx");
		t.newThread(new Runnable() {
			@Override
			public void run() {
				System.out.println("嗯哼");
			}
		}).start();
	}
}
