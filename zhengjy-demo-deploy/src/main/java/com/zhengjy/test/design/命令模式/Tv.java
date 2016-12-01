package com.zhengjy.test.design.命令模式;

public class Tv {
	public int currentChannel =0;
	
	public void turnOn(){
		System.out.println("the is Tv.turnOn()");
	}
	
	public void turnOff(){
		System.out.println("the is Tv.turnOff()");
	}
	
	public void changeChnanel(int channel){
		this.currentChannel = channel;
		System.out.println("Now Tv channel is"+channel);
	}
}
