package com.zhengjy.test.thread;

public class Test {
	static Object o = new Object();
	public static  void get(int i){
		try {
			synchronized(o){
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName());
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0;i<10;i++){
					Thread.currentThread().setName("Thread---"+i);
					get(i);
					
				}
			}
		}).start();
	}
}
