package com.zhengjy.test.design.gs;

import java.util.LinkedList;

public class RequestQueue {
	  private LinkedList queue = new LinkedList();

	    public synchronized Request getRequest() {
	        while(queue.size() <= 0) {
	            try {
	                wait();	//等待知道有新的Request请求
	            }
	            catch(InterruptedException e) {}
	        }
	        return (Request) queue.removeFirst();//返回Request队列中的第一个请求
	    }

	    public synchronized void putRequest(Request request) {
	        queue.addLast(request);//加入新的Request请求
	        notifyAll();//通过getRequest()方法
	    }
}
