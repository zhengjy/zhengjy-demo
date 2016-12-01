package com.zhengjy.test.thread.ThreadLocal;

public class ThreadLocal3 {
	
	private static  Index num =new Index();
	
	private  static  ThreadLocal<Index> tl = new ThreadLocal<Index>(){
		/**
		 * 每个线程都会获取这个初始值的一个副本。而现在我们的初始值是一个定义好的对象，num这个对象是引用，引用的副本和引用所指向的对象就是同一个对象。
		 */
		@Override
		protected Index initialValue() {
			return  num;
//			return  new Index();
		}
	};
	public static void main(String[] args) {
		Thread[] t = new Thread[5];
		for(int j =0;j<5;j++){
			t[j] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					Index index = tl.get();
					for (int i = 0; i < 1000; i++) {                                          
						index.increase();
					}
					tl.set(index);
					System.out.println(Thread.currentThread().getName() + " : "+ index.num);
				}
			},"Thread-"+j);
			
		}
		for (Thread thread : t) {
			thread.start();
		}
	}
	
	
	
	static class Index {
		int num;
		public void increase() {
			num++;
		}
	}
}
