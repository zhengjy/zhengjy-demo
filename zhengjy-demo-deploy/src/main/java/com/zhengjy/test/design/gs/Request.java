package com.zhengjy.test.design.gs;

import com.zhengjy.test.design.futrue.Data;

public class Request {
	private String name;
	
	private Data response;//请求返回值
	public Request(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Request [name=" + name + "]";
	}
	public synchronized Data getResponse() {
		return response;
	}
	public synchronized void setResponse(Data response) {
		this.response = response;
	}
	
	
}
