package com.zhengjy.test.design.gs;

public class Test {
	public static void main(String[] args) {
		RequestQueue rq = new RequestQueue();//请求队列
		for(int i=0;i<10;i++){
			new ServerThread(rq, "ServerThread==="+i).start();//服务端线程开启
		}
		for(int i=0;i<10;i++){
			new ClientThread(rq, "ClientThread---"+i).start();//请求线程开启
		}
	}
}
