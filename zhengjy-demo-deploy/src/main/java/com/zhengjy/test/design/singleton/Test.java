package com.zhengjy.test.design.singleton;

public class Test {
	@org.junit.jupiter.api.Test
	public void test01(){
		for(int i=0;i<5;i++){
			new Thread(new Runnable() {
				long t = System.currentTimeMillis();
				@Override
				public void run() {
						for(int i=0;i<100000;i++){
						//	Singleton.getSingleton();
							LazySingleton.getLazySingleton();
							System.out.println("spend："+(System.currentTimeMillis() -t) );
							System.out.println("线程名称： "+Thread.currentThread().getName());
						}
				}
			}).start();
		}
	}
}
