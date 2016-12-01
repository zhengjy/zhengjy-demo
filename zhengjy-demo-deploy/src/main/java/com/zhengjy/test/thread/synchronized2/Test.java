package com.zhengjy.test.thread.synchronized2;

public class Test {
	public static void main(String[] args) {
		User u = new User();
		u.setPrice(100);
		MyThread m1 = new MyThread("小郑", u, -100);
		MyThread m2 = new MyThread("小郑", u, -200);
		MyThread m3 = new MyThread("小郑", u, 200);
		MyThread m4 = new MyThread("小郑", u, 300);
		MyThread m5 = new MyThread("小郑", u, 100);
		m1.start();
		m2.start();
		m3.start();
		m4.start();
		m5.start();
	}
}

class MyThread extends Thread{
	private int p;
	private User u;
	MyThread(String name, User u, int y) {
        super(name); 
        this.u = u; 
        this.p = y; 
	} 
	public void run(){
		u.edit(p);
	}
}

class User{
	private int price;
	public synchronized void edit(int price){
		try {
			Thread.sleep(10);
			this.price += price;
			System.out.println(Thread.currentThread().getName() +"运行结束，增加了"+price+"，目前账户余额为："+this.price);
			Thread.sleep(10);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
