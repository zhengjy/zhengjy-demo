package com.zhengjy.test.thread.ThreadLocal;

public class ThreadLocalTest2 {
	//创建一个Integer型的线程本地变量
	public static  ThreadLocal<Integer> tl = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	
	public static void main(String[] args) {
		Thread[] t = new Thread[5];
		for(int j=0;j<5;j++){
			t[j] = new Thread(new Runnable(){
				@Override
				public void run() {
					//获取当前线程的本地变量，然后累加5次
					int num = tl.get();
					for(int i=0;i<5;i++){
						num++;
					}
					//重新设置累加后的本地变量
					tl.set(num);
					System.out.println(Thread.currentThread().getName() + " : "+ tl.get());
				}
				
			},"Thread-"+j);
		}
		
		for (Thread thread : t) {
			thread.start();
		}
	}


	
}  
