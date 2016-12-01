package com.zhengjy.test.thread.wait;
/**
 * 01）主线程通过new ThreadA("t1") 新建线程t1,随后通过synchronized(t1)获取“t1对象的同步锁”，然后调用t1.start()启动“线程t1”;
 * 02）主线程通过t1.wait()释放t1对象的锁并且进入等待（阻塞）状态，等待t1对象上的线程通过notify()来将其唤醒。
 * 03）线程t1运行后，通过synchronized(this)获取当前对象的锁；接着调用notify()唤醒当前对象上的等待线程。也就是唤醒主线程。
 * 04）线程t1运行完毕后，释放当前对象的锁。接着主线程获取t1对象的锁，接着往下执行
 * @author zhengjy
 *
 */
public class WaitTest {
	public static void main(String[] args) throws Exception {
		ThreadA t1 = new ThreadA("t1");
		synchronized(t1){
			//启动线程t1
			System.out.println(Thread.currentThread().getName()+" start t1");
			t1.start();
			//主线程等待t线程notify()唤醒
			
			System.out.println(Thread.currentThread().getName()+" wait() ");
			t1.wait();
			System.out.println(Thread.currentThread().getName()+" continue()");
			
		}
	}
}

class ThreadA	extends Thread{
	public ThreadA(String name){
		super(name);
	}
		public void run(){
			synchronized(this){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() +" call notify()");
				//唤醒当前的wait现场
				notify();
			}
		}
}