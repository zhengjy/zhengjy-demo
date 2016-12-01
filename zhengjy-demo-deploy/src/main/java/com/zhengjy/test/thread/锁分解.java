package com.zhengjy.test.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 锁分解：
 * 	锁分解实现方法有2种：
 * 	1)缩小锁的范围：锁分解的核心缩小无关的代码块，如果在一个方法中有一部分的代码与锁无关，一部分的代码与锁有关，那么可以缩小这个锁的范围，
 * 这样锁操作的代码块减少，锁竞争的可能性也降低
 * 	2)减少锁的的粒度：如果一个锁需要保护多个互相独立的变量，那么可以将一个锁分解多个锁，并且每个锁保护一个变量。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月28日 下午3:08:51 <br/>
 */
public class 锁分解 {
	
	/**
	 缩小锁的范围：如果在一个方法中有一部分的代码与锁无关，一部分的代码与锁有关，可以缩小这个锁的范围，这样锁操作的代码块减少，
	锁竞争的可能性也降低
	 * */
	
	//粗粒度直接在方法上加synchronized，这样会提高锁冲突
	public synchronized void synchronizedMethod1(){
		prefix();
		try{
		TimeUnit.SECONDS.sleep(1);
		}catch(Exception e){
		}
		post();
		
	}
	
	/***修正写法*/
	//假设prefix()和post()方法是线程安全的（与锁无关的代码）
	public synchronized void synchronizedMethod2(){
		prefix();
		synchronized(this){//synchronized 代码快只保护有竞争的代码
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		post();
	} 
	
	
	private void post() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void prefix() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class  锁分解2{
	
	
	private final Set<String> allUsers = new HashSet<String>();
	private final Set<String> allComputers = new HashSet<String>();
	//传统写法
	public synchronized void addUser(String user){//公用一把锁
		allUsers.add(user);
	}
	
	public synchronized void addComputers(String computers){
		allComputers.add(computers);
	}
	
	
	/***
	 如果一个锁需要保护多个相互独立的变量，那么可以将一个锁分解多个锁，并且每个锁保护保护一个变量
	 
	 * */
	//分解两把锁
	public void addUser2(String u){
		synchronized(allUsers){
			allUsers.add(u);
		}
	}
	public void addComputers2(String computers){
		synchronized(allComputers){
			allComputers.add(computers);
		}
	}
	
}




