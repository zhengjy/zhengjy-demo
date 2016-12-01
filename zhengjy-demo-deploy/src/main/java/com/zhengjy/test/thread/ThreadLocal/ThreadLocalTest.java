package com.zhengjy.test.thread.ThreadLocal;

import com.zhengjy.test.reflection.User2;

public class ThreadLocalTest {
	ThreadLocal<User2> tl = new ThreadLocal<User2>(){
		User2 u=null;
		@Override
		public User2 initialValue(){
			if(u==null){
				u= new User2();
				tl.set(u);
			}
			return u;
		}
	};
	
	public User2 get(){
		return tl.get();
	}
}
