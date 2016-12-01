package com.zhengjy.test.design.demo.命令模式.t2;

public class GarageDoor {
	public void up(){
		System.out.println("Garage Door is Open");
	}
	
	public void down(){
		System.out.println("garage Dorr is down");
	}
	
	public void stop(){
		System.out.println("Garage Door is stop");
	}
	
	public void lightOn(){
		System.out.println("Garage Door is lightOn");
	}
	
	public void lightOff(){
		System.out.println("Garage Door is lightOff");
	}
}
